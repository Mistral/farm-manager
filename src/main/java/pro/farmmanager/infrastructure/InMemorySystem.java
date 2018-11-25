package pro.farmmanager.infrastructure;

import pro.farmmanager.farmlands.FarmlandConfiguration;
import pro.farmmanager.farmlands.FarmlandFacade;
import pro.farmmanager.operation.OperationConfiguration;
import pro.farmmanager.operation.OperationFacade;
import pro.farmmanager.user.UserConfiguration;
import pro.farmmanager.user.UserFacade;

import java.util.UUID;

public final class InMemorySystem {

    private final OperationFacade operationFacade;

    private final FarmlandFacade farmlandFacade;

    private final UserFacade userFacade;

    public InMemorySystem() {
        userFacade = new UserConfiguration().userFacade();
        farmlandFacade = new FarmlandConfiguration().farmlandFacade();
        operationFacade = new OperationConfiguration().operationFacade();
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

    public OperationFacade getOperationFacade() {
        return operationFacade;
    }

}
