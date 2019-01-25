package pro.farmmanager.operation.dto;

import pro.farmmanager.shared_kernel.Dose;
import pro.farmmanager.shared_kernel.Money;

import java.util.UUID;

public class NewOperationResourceDto {

    private UUID resourceId;

    private UUID variantId;

    private Dose dose;

    private Money unitCost;

    public static NewOperationResourceDto create(UUID resourceId, Dose dose, Money cost) {
        return new NewOperationResourceDto(resourceId, null, dose, cost);
    }

    public static NewOperationResourceDto createWithVariant(UUID resourceId, UUID variantId, Dose dose, Money cost) {
        return new NewOperationResourceDto(resourceId, variantId, dose, cost);
    }

    private NewOperationResourceDto(UUID resourceId, UUID variantId, Dose dose, Money unitCost) {
        this.resourceId = resourceId;
        this.variantId = variantId;
        this.dose = dose;
        this.unitCost = unitCost;
    }

    public UUID getResourceId() {
        return resourceId;
    }

    public UUID getVariantId() {
        return variantId;
    }

    public Dose getDose() {
        return dose;
    }

    public Money getUnitCost() {
        return unitCost;
    }

}
