package pro.farmmanager.operation;

import pro.farmmanager.shared_kernel.BaseEntity;
import pro.farmmanager.shared_kernel.Money;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
class Operation extends BaseEntity {

    public enum Status {
        PENDING,
        CANCELLED,
        DONE;
    }

    @Column(columnDefinition = "BINARY(16)", length = 16)
    private UUID farmlandId;

    @Enumerated
    private OperationType type;

    @Enumerated
    private Status status;

    private LocalDateTime plannedAt;

    private LocalDateTime doneAt;

    @OneToMany
    @JoinColumn(name = "operation")
    private Set<OperationResource> resources = new HashSet<>();

    @Embedded
    private Money unitCost;

    @Transient
    private Money operationCost = Money.ZERO;

    @Transient
    private Money materialCost = Money.ZERO;

    @Transient
    private Float area;

    private Operation(UUID farmlandId, OperationType type, Money cost, List<OperationResourceDto> resources) {
        this.farmlandId = farmlandId;
        this.type = type;
        this.unitCost = cost;

        List<OperationResource> operationResources = resources.stream()
                                                              .map(OperationResource::fromDto)
                                                              .collect(Collectors.toList());
        this.resources.addAll(operationResources);
    }

    private Operation(UUID farmlandId, OperationType type, Money cost) {
        this.farmlandId = farmlandId;
        this.type = type;
        this.unitCost = cost;
    }

    public static Operation create(UUID farmlandId, OperationType type, Money cost) {
        return new Operation(farmlandId, type, cost);
    }

    public static Operation createWithResource(UUID farmlandId, OperationType type, Money unitCost, List<OperationResourceDto> resources) {
        return new Operation(farmlandId, type, unitCost, resources);
    }

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

    void calculateCost(Float area) {
        this.area = area;

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
        calculateCost(this.area);
    }

    public UUID getFarmlandId() {
        return farmlandId;
    }

    public OperationDto toDto() {
        Set<OperationResourceDto> operationResources = resources.stream()
                                                                .map(OperationResource::toDto)
                                                                .collect(Collectors.toSet());

        return new OperationDto(farmlandId, type, unitCost, operationCost.add(materialCost), operationCost, materialCost, operationResources);
    }

}
