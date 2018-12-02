package pro.farmmanager.farmlands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.farmmanager.farmlands.dto.FarmlandCreateRequest;
import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.user.UserFacade;

import java.util.List;
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
        return farmlandFacade.getFarmlandById(farmlandId)
                             .map(farmland -> new ResponseEntity<>(farmland, HttpStatus.OK))
                             .getOrElse(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    HttpEntity<List<FarmlandDto>> getFarmlandsForUser() {
        List<FarmlandDto> farmlands = farmlandFacade.getFarmlandsForUser(UserFacade.authorizedId);
        return new ResponseEntity<>(farmlands, HttpStatus.OK);
    }

    @PostMapping
    HttpEntity<String> createFarmland(@RequestBody FarmlandCreateRequest farmlandCreateRequest) {
        return farmlandFacade.createFarmland(farmlandCreateRequest.getName(), farmlandCreateRequest.getArea(), UserFacade.authorizedId)
                             .map(f -> new ResponseEntity<>(f.toString(), HttpStatus.CREATED))
                             .getOrElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

}
