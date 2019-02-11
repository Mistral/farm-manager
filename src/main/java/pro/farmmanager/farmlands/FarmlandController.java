package pro.farmmanager.farmlands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.farmmanager.farmlands.dto.FarmlandCreateRequest;
import pro.farmmanager.farmlands.dto.FarmlandDto;
import pro.farmmanager.user.UserFacade;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "farmlands")
public class FarmlandController {

    private final FarmlandFacade farmlandFacade;

    private final UserFacade userFacade;

    @Autowired
    FarmlandController(FarmlandFacade farmlandFacade, UserFacade userFacade) {
        this.farmlandFacade = farmlandFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/{farmlandId}")
    ResponseEntity getFarmland(@PathVariable UUID farmlandId) {
        return farmlandFacade.getFarmlandById(farmlandId)
                             .map(farmland -> new ResponseEntity<>(farmland, HttpStatus.OK))
                             .getOrElse(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    ResponseEntity getFarmlandsForUser() {
        List<FarmlandDto> farmlands = farmlandFacade.getFarmlandsForUser(UserFacade.authorizedId);
        return new ResponseEntity<>(farmlands, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity createFarmland(@RequestBody @Valid FarmlandCreateRequest farmlandCreateRequest, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return farmlandFacade.createFarmland(farmlandCreateRequest.getName(), farmlandCreateRequest.getArea(), UserFacade.authorizedId)
                                 .map(f -> ResponseEntity.created(URI.create("farmlands/" + f.toString())).body(f))
                                 .getOrElse(() -> ResponseEntity.badRequest().build());
        }
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }

}
