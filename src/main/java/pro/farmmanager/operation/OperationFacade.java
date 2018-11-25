package pro.farmmanager.operation;

import pro.farmmanager.shared_kernel.Money;

import java.util.UUID;

public class OperationFacade {

    final private OperationManager operationManager;

    OperationFacade(OperationManager operationManager) {
        this.operationManager = operationManager;
    }

    public UUID createOperation(UUID farmlandId, OperationType type, Money cost) {
        Operation operation = operationManager.createOperation(farmlandId, type, cost);
        return operation.getId();
    }

}
