package pro.farmmanager.operation;

import pro.farmmanager.infrastructure.SpringRepositoryAdapter;

import java.util.List;
import java.util.UUID;

class OperationSpringRepositoryAdapter extends SpringRepositoryAdapter<Operation> implements OperationRepository {

    private OperationSpringRepository repository;

    public OperationSpringRepositoryAdapter(OperationSpringRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Operation> findByFarmlandId(UUID farmlandId) {
        return repository.findByFarmlandId(farmlandId);
    }

}
