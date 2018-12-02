package pro.farmmanager.farmlands;

import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.farmmanager.farmlands.dto.FarmlandCreateRequest;
import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.user.UserFacade;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/farmlands")
public class FarmlandController {

    private final FarmlandFacade farmlandFacade;

    private final UserFacade userFacade;

    @Autowired
    FarmlandController(FarmlandFacade farmlandFacade, UserFacade userFacade) {
        this.farmlandFacade = farmlandFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/{farmlandId}")
    HttpEntity<FarmlandDto> getFarmland(@PathVariable UUID farmlandId) {
        Option<FarmlandDto> farmlandDto = farmlandFacade.getFarmlandById(farmlandId);

        if (farmlandDto.isDefined()) {
            return new ResponseEntity<>(farmlandDto.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    HttpEntity<List<FarmlandDto>> getFarmlandsForUser() {
        List<FarmlandDto> farmlandDtos = farmlandFacade.getFarmlandsForUser(UserFacade.authorizedId);
        return new ResponseEntity<>(farmlandDtos, HttpStatus.OK);
    }

    @PostMapping
    HttpEntity<String> createFarmland(@RequestBody FarmlandCreateRequest farmlandCreateRequest) {
        Either<FarmlandError, UUID> farmlandId = farmlandFacade
            .createFarmland(farmlandCreateRequest.getName(), farmlandCreateRequest.getArea(), UserFacade.authorizedId);

        return farmlandId.map(f -> new ResponseEntity<>(f.toString(), HttpStatus.CREATED))
                         .getOrElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

}
