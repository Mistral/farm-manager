package pro.farmmanager.operation;

import io.vavr.control.Either;
import io.vavr.control.Option;
import pro.farmmanager.farmlands.FarmlandFacade;
import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.shared_kernel.Money;

import java.util.List;
import java.util.UUID;

class OperationManager {

    private final OperationRepository operationRepository;

    private final FarmlandFacade farmlandFacade;

    OperationManager(OperationRepository operationRepository, FarmlandFacade farmlandFacade) {
        this.operationRepository = operationRepository;
        this.farmlandFacade = farmlandFacade;
    }

    Either<OperationError, Operation> createOperation(UUID farmlandId, OperationType type, Money cost) {
        return farmlandFacade.getFarmlandById(farmlandId)
                             .map(farmlandDto -> {
                                 Operation operation = Operation.create(farmlandId, type, cost);
                                 operationRepository.save(operation);
                                 calculateOperationCost(operation);
                                 return operation;
                             })
                             .toEither(OperationError.INVALID_FARMLAND);
    }

    Option<Operation> getOperationById(UUID operationId) {
        return Option.ofOptional(operationRepository.findById(operationId));
    }

    List<Operation> getOperationsByFarmlandId(UUID farmlandId) {
        return operationRepository.findByFarmlandId(farmlandId);
    }

    private void calculateOperationCost(Operation operation) {
        Float area = farmlandFacade.getFarmlandById(operation.getFarmlandId())
                                   .map(FarmlandDto::getArea)
                                   .get();
        operation.calculateCost(area);
    }

    Either<OperationError, Operation> createResourceOperation(UUID farmlandId, OperationType type, Money unitCost, List<OperationResourceDto> resources) {
        return farmlandFacade.getFarmlandById(farmlandId)
                             .map(farmlandDto -> {
                                 Operation operation = Operation.createWithResource(farmlandId, type, unitCost, resources);
                                 operationRepository.save(operation);
                                 calculateOperationCost(operation);
                                 return operation;
                             })
                             .toEither(OperationError.INVALID_FARMLAND);
    }

}
