package pro.farmmanager.farmlands;

import pro.farmmanager.user.UserFacade;

import java.util.UUID;

public class FarmlandFacade {

    private FarmlandManager farmlandManager;

    private UserFacade userFacade;

    FarmlandFacade(FarmlandManager farmlandManager, UserFacade userFacade) {
        this.farmlandManager = farmlandManager;
        this.userFacade = userFacade;
    }

    UUID createFarmland(String name, Float area) {
        return farmlandManager.createFarmland(name, area, userFacade.getAuthorizedUser());
    }

}
