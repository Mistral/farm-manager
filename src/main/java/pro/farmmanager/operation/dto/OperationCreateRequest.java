package pro.farmmanager.operation.dto;

import pro.farmmanager.operation.OperationType;

import java.util.List;
import java.util.UUID;

public class OperationCreateRequest {

    private UUID farmlandId;

    private OperationType operationType;

    private List<NewOperationResourceForm> resources;

    private Double cost;

    public UUID getFarmlandId() {
        return farmlandId;
    }

    public void setFarmlandId(UUID farmlandId) {
        this.farmlandId = farmlandId;
    }

    public List<NewOperationResourceForm> getResources() {
        return resources;
    }

    public void setResources(List<NewOperationResourceForm> resources) {
        this.resources = resources;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

}
