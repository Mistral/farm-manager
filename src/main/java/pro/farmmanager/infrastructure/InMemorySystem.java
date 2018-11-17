package pro.farmmanager.infrastructure;

import pro.farmmanager.farmlands.FarmlandConfiguration;
import pro.farmmanager.farmlands.FarmlandFacade;

public class InMemorySystem {

    private FarmlandFacade farmlandFacade;

    public InMemorySystem() {
        farmlandFacade = new FarmlandConfiguration().farmlandFacade();
    }

    public FarmlandFacade getFarmlandFacade() {
        return farmlandFacade;
    }

}
