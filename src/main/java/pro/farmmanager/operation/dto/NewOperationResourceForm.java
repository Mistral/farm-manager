package pro.farmmanager.operation.dto;

import pro.farmmanager.shared_kernel.Dose;
import pro.farmmanager.shared_kernel.Money;

import java.util.UUID;

public class NewOperationResourceForm {

    private UUID resourceId;

    private UUID resourceVariantId;

    private Double dose;

    private Dose.Unit unit;

    private Double cost;

    public UUID getResourceId() {
        return resourceId;
    }

    public void setResourceId(UUID resourceId) {
        this.resourceId = resourceId;
    }

    public UUID getResourceVariantId() {
        return resourceVariantId;
    }

    public void setResourceVariantId(UUID resourceVariantId) {
        this.resourceVariantId = resourceVariantId;
    }

    public Double getDose() {
        return dose;
    }

    public void setDose(Double dose) {
        this.dose = dose;
    }

    public Dose.Unit getUnit() {
        return unit;
    }

    public void setUnit(Dose.Unit unit) {
        this.unit = unit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public NewOperationResourceDto toNewOperationResourceDto() {
        if (resourceVariantId != null) {
            return NewOperationResourceDto.createWithVariant(resourceId, resourceVariantId, Dose.of(dose, unit), new Money(cost));
        }
        else {
            return NewOperationResourceDto.create(resourceId, Dose.of(dose, unit), new Money(cost));
        }
    }

}
