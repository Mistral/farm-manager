package pro.farmmanager.operation;

import pro.farmmanager.shared_kernel.BaseEntity;
import pro.farmmanager.shared_kernel.Money;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
class Operation extends BaseEntity {

    public enum Type {
        // OperationResource
        SEEDING,
        SPRAYING,
        MANURING,

        // Mechanical
        HARVESTING,
        LIMING, //wapnowanie
        DISKING, // talerzowanie
        ROLLING, // wałowanie
        HARROWING, // bronowanie
        PLOWING, // oranie
        CULTIVATION, // uprawa
    }

    public enum Status {
        PENDING,
        CANCELLED,
        DONE;
    }

    @Id
    @GeneratedValue
    private Long id;

    private Long farmlandId;

    @Enumerated
    private Type type;

    @Enumerated
    private Status status;

    private LocalDateTime plannedAt;

    private LocalDateTime doneAt;

    private Double area;

    @OneToMany
    @JoinColumn(name = "operation")
    @OrderColumn
    private List<OperationResource> resources;

    @Embedded
    private Money unitCost;

    @Transient
    private Money operationCost = Money.ZERO;

    @Transient
    private Money materialCost = Money.ZERO;

    public void end() {
        this.status = Status.DONE;
        this.doneAt = LocalDateTime.now();
    }

    public void planAt(LocalDateTime plannedDate) {
        this.plannedAt = plannedDate;
        this.status = Status.PENDING;
    }

    public void cancel() {
        this.status = Status.CANCELLED;
        this.doneAt = LocalDateTime.now();
    }

    private void recalculateCost() {
        operationCost = Money.ZERO;
        operationCost = operationCost.add(this.unitCost).multiplyBy(this.area);

        materialCost = Money.ZERO;
        resources.forEach(r -> materialCost = materialCost.add(r.getUnitCost()));
        materialCost = materialCost.multiplyBy(this.area);
    }

    public Money getOperationCost() {
        return operationCost;
    }

    public Money getMaterialCost() {
        return materialCost;
    }

    public Money getTotalCost() {
        return operationCost.add(materialCost);
    }

    public void useResource(OperationResource operationResource) {
        this.resources.add(operationResource);
        recalculateCost();
    }

}
