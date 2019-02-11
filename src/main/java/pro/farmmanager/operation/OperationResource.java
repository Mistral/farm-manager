package pro.farmmanager.operation;

import pro.farmmanager.operation.dto.NewOperationResourceDto;
import pro.farmmanager.shared_kernel.BaseEntity;
import pro.farmmanager.shared_kernel.Dose;
import pro.farmmanager.shared_kernel.Money;

import javax.persistence.*;
import java.util.UUID;

@Entity
class OperationResource extends BaseEntity {

    private UUID resourceId;

    private UUID resourceVariantId;

    @Embedded
    private Money unitCost;

    @Embedded
    private Dose dose;

    public static OperationResource create(UUID resourceId) {
        return new OperationResource(resourceId, null, null, Money.ZERO);
    }

    public static OperationResource createWithoutVariant(UUID resourceId, NewOperationResourceDto operationResourceDto) {
        return new OperationResource(resourceId, null, operationResourceDto.getDose(), operationResourceDto.getUnitCost());
    }

    public static OperationResource createWithVariant(UUID resourceId, UUID variantId, NewOperationResourceDto operationResourceDto) {
        return create(resourceId, variantId, operationResourceDto);
    }

    public static OperationResource create(UUID resourceId, UUID variantId, NewOperationResourceDto operationResourceDto) {
        return new OperationResource(resourceId, variantId, operationResourceDto.getDose(), operationResourceDto.getUnitCost());
    }

    public static OperationResource createWithDose(UUID resourceId, Dose dose) {
        return new OperationResource(resourceId, null, dose, Money.ZERO);
    }

    public static OperationResource createWithMoney(UUID resourceId, Money money) {
        return new OperationResource(resourceId, null, null, money);
    }

    public static OperationResource createWithDoseAndMoney(UUID resourceId, Money money, Dose dose) {
        return new OperationResource(resourceId, null, dose, money);
    }

    public static OperationResource createWithVariantAndMoney(UUID resourceId, UUID variantId, Money money) {
        return new OperationResource(resourceId, variantId, null, money);
    }

    public static OperationResource createWithVariantAndMoneyAndDose(UUID resourceId, UUID variantId, Money money, Dose dose) {
        return new OperationResource(resourceId, variantId, dose, money);
    }

    public static OperationResource fromDto(OperationResourceDto dto) {
        return new OperationResource(dto.getResourceId(), dto.getResourceVariantId(), dto.getDose(), dto.getUnitCost());
    }

    private OperationResource(UUID resourceId, UUID variantId, Dose dose, Money money) {
        this.resourceId = resourceId;
        this.resourceVariantId = variantId;
        this.dose = dose;
        this.unitCost = money;
    }

    private OperationResource() {

    }

    //    static OperationResource createFrom(NewOperationResourceDto resourceDto) {
    //        return new OperationResource(resourceDto.getResourceId());
    //    }

    public Money getUnitCost() {
        return this.unitCost;
    }

    public OperationResourceDto toDto() {
        //        return new OperationResourceDto(id, operationId, resource, variant, dose, unitCost, totalCost);
        return null;
    }

}
