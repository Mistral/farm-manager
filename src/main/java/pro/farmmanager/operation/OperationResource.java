package pro.farmmanager.operation;

import pro.farmmanager.operation.dto.NewOperationResourceDto;
import pro.farmmanager.operation.dto.NewResourceVariantDto;
import pro.farmmanager.shared_kernel.BaseEntity;
import pro.farmmanager.shared_kernel.Dose;
import pro.farmmanager.shared_kernel.Money;

import javax.persistence.*;

@Entity
class OperationResource extends BaseEntity {

    @ManyToOne
    private Resource resource;

    @ManyToOne
    private ResourceVariant variant;

    @Embedded
    private Money unitCost;

    @Embedded
    private final Dose dose;

    public static OperationResource create(Resource resource) {
        return new OperationResource(resource, null, null, Money.ZERO);
    }

    public static OperationResource createWithoutVariant(Resource resource, NewOperationResourceDto operationResourceDto) {
        return new OperationResource(resource, null, operationResourceDto.getDose(), operationResourceDto.getUnitCost());
    }

    public static OperationResource createWithVariant(Resource resource, ResourceVariant variant, NewOperationResourceDto operationResourceDto) {
        return create(resource, variant, operationResourceDto);
    }

    public static OperationResource create(Resource resource, ResourceVariant variant, NewOperationResourceDto operationResourceDto) {
        return new OperationResource(resource, variant, operationResourceDto.getDose(), operationResourceDto.getUnitCost());
    }

    public static OperationResource createWithDose(Resource resource, Dose dose) {
        return new OperationResource(resource, null, dose, Money.ZERO);
    }

    public static OperationResource createWithMoney(Resource resource, Money money) {
        return new OperationResource(resource, null, null, money);
    }

    public static OperationResource createWithDoseAndMoney(Resource resource, Money money, Dose dose) {
        return new OperationResource(resource, null, dose, money);
    }

    public static OperationResource createWithVariantAndMoney(Resource resource, ResourceVariant variant, Money money) {
        return new OperationResource(resource, variant, null, money);
    }

    public static OperationResource createWithVariantAndMoneyAndDose(Resource resource, ResourceVariant variant, Money money, Dose dose) {
        return new OperationResource(resource, variant, dose, money);
    }

    public static OperationResource fromDto(OperationResourceDto dto) {
        return new OperationResource(Resource.from(dto.getResource()), ResourceVariant.from(dto.getVariant()), dto.getDose(), dto.getUnitCost());
    }

    private OperationResource(Resource resource, ResourceVariant variant, Dose dose, Money money) {
        this.resource = resource;
        this.variant = variant;
        this.dose = dose;
        this.unitCost = money;
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
