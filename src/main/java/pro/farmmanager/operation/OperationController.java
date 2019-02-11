package pro.farmmanager.operation;

import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.farmmanager.farmlands.FarmlandFacade;
import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.operation.dto.OperationCreateRequest;
import pro.farmmanager.operation.dto.NewOperationResourceDto;
import pro.farmmanager.operation.dto.NewOperationResourceForm;
import pro.farmmanager.shared_kernel.Money;
import pro.farmmanager.user.UserFacade;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "operations")
class OperationController {

    private OperationFacade operationFacade;

    private FarmlandFacade farmlandFacade;

    private UserFacade userFacade;

    @Autowired
    OperationController(OperationFacade operationFacade, FarmlandFacade farmlandFacade, UserFacade userFacade) {
        this.operationFacade = operationFacade;
        this.farmlandFacade = farmlandFacade;
        this.userFacade = userFacade;
    }

    @PostMapping
    public ResponseEntity storeOperation(@RequestBody OperationCreateRequest form) {
        Either<OperationError, UUID> operation;
        if (form.getResources() != null && form.getResources().size() > 0) {
            List<NewOperationResourceDto> resourceDtos = form.getResources()
                                                             .stream()
                                                             .map(NewOperationResourceForm::toNewOperationResourceDto)
                                                             .collect(Collectors.toList());
            operation = operationFacade.createResourceOperation(form.getFarmlandId(), form.getOperationType(), new Money(form.getCost()), resourceDtos);
        }
        else {
            operation = operationFacade.createOperation(form.getFarmlandId(), form.getOperationType(), new Money(form.getCost()));
        }
        return operation.map(operationId -> operationFacade.getOperationById(operationId)
                                                           .map(operationDto -> ResponseEntity.created(URI.create("/operations/" + operationDto.getId()))
                                                                                              .body(operationDto))
                                                           .get())
                        .mapLeft(operationError -> ResponseEntity.status(HttpStatus.BAD_REQUEST))
                        .get();
    }

    @GetMapping("/{operationId}")
    public ResponseEntity getOperation(@PathVariable UUID operationId) {
        return operationFacade.getOperationById(operationId)
                              .map(operationDto -> ResponseEntity.status(HttpStatus.OK)
                                                                 .body(operationDto))
                              .getOrElse(ResponseEntity.notFound()
                                                       .build());
    }

    @GetMapping
    public ResponseEntity getOperations(@RequestParam(required = false) UUID farmlandId) {
        if (farmlandId != null) {
            return ResponseEntity.ok(operationFacade.getOperations(farmlandId));
        }
        else {
            List<OperationDto> operations = farmlandFacade.getFarmlands()
                                                          .stream()
                                                          .map(FarmlandDto::getId)
                                                          .map(farmId -> operationFacade.getOperations(farmId))
                                                          .flatMap(Collection::stream)
                                                          .collect(Collectors.toList());

            return ResponseEntity.ok(operations);
        }
    }

}
