package pro.farmmanager.operation;

import pro.farmmanager.farmlands.FarmlandFacade;
import pro.farmmanager.farmlands.exceptions.FarmlandNotFoundException;
import pro.farmmanager.shared_kernel.Money;

import java.util.UUID;

class OperationManager {

    private final OperationRepository operationRepository;

    private final FarmlandFacade farmlandFacade;

    OperationManager(OperationRepository operationRepository, FarmlandFacade farmlandFacade) {
        this.operationRepository = operationRepository;
        this.farmlandFacade = farmlandFacade;
    }

    Operation createOperation(UUID farmlandId, OperationType type, Money cost) {
            farmlandFacade.getFarmlandById(farmlandId);
        return null;
    }

}
