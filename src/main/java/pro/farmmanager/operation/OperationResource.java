package pro.farmmanager.operation;

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

    static public OperationResource create(Resource resource) {
        return new OperationResource(resource, null, null, Money.ZERO);
    }

    static public OperationResource createWithVariant(Resource resource, ResourceVariant variant) {
        return new OperationResource(resource, variant, null, Money.ZERO);
    }

    static public OperationResource createWithDose(Resource resource, Dose dose) {
        return new OperationResource(resource, null, dose, Money.ZERO);
    }

    static public OperationResource createWithMoney(Resource resource, Money money) {
        return new OperationResource(resource, null, null, money);
    }

    static public OperationResource createWithDoseAndMoney(Resource resource, Money money, Dose dose) {
        return new OperationResource(resource, null, dose, money);
    }

    static public OperationResource createWithVariantAndMoney(Resource resource, ResourceVariant variant, Money money) {
        return new OperationResource(resource, variant, null, money);
    }

    static public OperationResource createWithVariantAndMoneyAndDose(Resource resource, ResourceVariant variant, Money money, Dose dose) {
        return new OperationResource(resource, variant, dose, money);
    }

    private OperationResource(Resource resource, ResourceVariant variant, Dose dose, Money money) {
        this.resource = resource;
        this.variant = variant;
        this.dose = dose;
        this.unitCost = money;
    }

    public Money getUnitCost() {
        return this.unitCost;
    }

}
