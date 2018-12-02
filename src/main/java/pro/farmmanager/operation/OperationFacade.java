package pro.farmmanager.operation;

import io.vavr.control.Either;
import io.vavr.control.Option;
import pro.farmmanager.shared_kernel.Money;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OperationFacade {

    final private OperationManager operationManager;

    OperationFacade(OperationManager operationManager) {
        this.operationManager = operationManager;
    }

    public Either<OperationError, UUID> createOperation(UUID farmlandId, OperationType type, Money cost) {
        return operationManager.createOperation(farmlandId, type, cost).map(Operation::getId);
    }

    public Option<OperationDto> getOperationById(UUID operationId) {
        return operationManager.getOperationById(operationId).map(Operation::toDto);
    }

    public List<OperationDto> getOperations(UUID farmlandId) {
        return operationManager.getOperationsByFarmlandId(farmlandId)
                               .stream()
                               .map(Operation::toDto)
                               .collect(Collectors.toList());
    }

}
