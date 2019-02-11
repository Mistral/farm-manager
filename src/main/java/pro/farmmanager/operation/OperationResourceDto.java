package pro.farmmanager.operation;

import pro.farmmanager.resources.ResourceDto;
import pro.farmmanager.resources.ResourceVariantDto;
import pro.farmmanager.shared_kernel.Dose;
import pro.farmmanager.shared_kernel.Money;

import java.util.UUID;

public class OperationResourceDto {

    private UUID id;

    private UUID operationId;

    private UUID resourceId;

    private UUID resourceVariantId;

    private Dose dose;

    private Money unitCost;

    private Money totalCost;

    public OperationResourceDto(UUID id, UUID operationId, UUID resourceId, UUID variantId, Dose dose, Money unitCost, Money totalCost) {
        this.id = id;
        this.operationId = operationId;
        this.resourceId = resourceId;
        this.resourceVariantId = variantId;
        this.dose = dose;
        this.unitCost = unitCost;
        this.totalCost = totalCost;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOperationId() {
        return operationId;
    }

    public UUID getResourceId() {
        return resourceId;
    }

    public UUID getResourceVariantId() {
        return resourceVariantId;
    }

    public Dose getDose() {
        return dose;
    }

    public Money getUnitCost() {
        return unitCost;
    }

    public Money getTotalCost() {
        return totalCost;
    }

}
