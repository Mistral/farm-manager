package pro.farmmanager.infrastructure;

import pro.farmmanager.farmlands.FarmlandConfiguration;
import pro.farmmanager.farmlands.FarmlandFacade;
import pro.farmmanager.user.UserConfiguration;
import pro.farmmanager.user.UserFacade;

import java.util.UUID;

public class InMemorySystem {

    private FarmlandFacade farmlandFacade;

    private UserFacade userFacade;

    public InMemorySystem() {
        userFacade = new UserConfiguration().userFacade();
        farmlandFacade = new FarmlandConfiguration().farmlandFacade();
    }

    public FarmlandFacade getFarmlandFacade() {
        return farmlandFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public UUID getCustomUserId() {
        return UUID.randomUUID();
    }

}
