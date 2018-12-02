package pro.farmmanager.operation;

import pro.farmmanager.infrastructure.InMemoryRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class OperationInMemoryRepository extends InMemoryRepository<Operation> implements OperationRepository {

    @Override
    public List<Operation> findByFarmlandId(UUID farmlandId) {
        return map.values()
                  .stream()
                  .filter(operation -> farmlandId.equals(operation.getFarmlandId()))
                  .collect(Collectors.toList());
    }

}
