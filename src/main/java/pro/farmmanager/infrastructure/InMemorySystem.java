package pro.farmmanager.infrastructure;

import pro.farmmanager.farmlands.FarmlandConfiguration;
import pro.farmmanager.farmlands.FarmlandFacade;
import pro.farmmanager.user.UserConfiguration;
import pro.farmmanager.user.UserFacade;

public class InMemorySystem {

    private FarmlandFacade farmlandFacade;

    private UserFacade userFacade;

    public InMemorySystem() {
        userFacade = new UserConfiguration().userFacade();
        farmlandFacade = new FarmlandConfiguration().farmlandFacade(userFacade);
    }

    public FarmlandFacade getFarmlandFacade() {
        return farmlandFacade;
    }

}
