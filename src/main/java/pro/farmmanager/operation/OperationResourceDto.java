package pro.farmmanager.operation;

import pro.farmmanager.shared_kernel.Dose;
import pro.farmmanager.shared_kernel.Money;

import java.util.UUID;

public class OperationResourceDto {

    private UUID id;

    private UUID operationId;

    private ResourceDto resource;

    private ResourceVariantDto variant;

    private Dose dose;

    private Money unitCost;

    private Money totalCost;

    public OperationResourceDto(UUID id, UUID operationId, ResourceDto resource, ResourceVariantDto variant, Dose dose, Money unitCost, Money totalCost) {
        this.id = id;
        this.operationId = operationId;
        this.resource = resource;
        this.variant = variant;
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

    public ResourceDto getResource() {
        return resource;
    }

    public ResourceVariantDto getVariant() {
        return variant;
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
