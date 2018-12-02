package pro.farmmanager.operation;

import pro.farmmanager.shared_kernel.Money;

import java.util.UUID;

public class OperationDto {

    private final UUID farmlandId;

    private Money unitCost;

    private Money totalCost;

    private Money operationCost;

    private Money resourceCost;

    private OperationType type;

    OperationDto(UUID farmlandId, OperationType type, Money unitCost, Money totalCost, Money operationCost, Money resourceCost) {
        this.farmlandId = farmlandId;
        this.type = type;
        this.unitCost = unitCost;
        this.totalCost = totalCost;
        this.operationCost = operationCost;
        this.resourceCost = resourceCost;
    }

    UUID getFarmlandId() {
        return farmlandId;
    }

    public OperationType getType() {
        return type;
    }

    public Money getUnitCost() {
        return unitCost;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public Money getOperationCost() {
        return operationCost;
    }

    public Money getResourceCost() {
        return resourceCost;
    }

}
