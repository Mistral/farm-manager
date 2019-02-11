package pro.farmmanager.operation;

import pro.farmmanager.shared_kernel.Money;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class OperationDto {

    private UUID id;

    private final UUID farmlandId;

    private Money unitCost;

    private Money totalCost;

    private Money operationCost;

    private Money resourceCost;

    private OperationType type;

    private Set<OperationResourceDto> resources = new LinkedHashSet<>();

    public OperationDto(UUID operationId, UUID farmlandId, OperationType type, Money unitCost, Money totalCost, Money operationCost, Money resourceCost, Set<OperationResourceDto> resources) {
        this.id = operationId;
        this.farmlandId = farmlandId;
        this.type = type;
        this.unitCost = unitCost;
        this.totalCost = totalCost;
        this.operationCost = operationCost;
        this.resourceCost = resourceCost;
        this.resources.addAll(resources);
    }

    public UUID getFarmlandId() {
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

    public Set<OperationResourceDto> getResources() {
        return resources;
    }

    public UUID getId() {
        return id;
    }

}
