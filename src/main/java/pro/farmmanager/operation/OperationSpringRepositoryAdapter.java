package pro.farmmanager.operation;

import pro.farmmanager.infrastructure.SpringRepositoryAdapter;

class OperationSpringRepositoryAdapter extends SpringRepositoryAdapter<Operation> implements OperationRepository {

    private SpringOperationRepository repository;

    public OperationSpringRepositoryAdapter(SpringOperationRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
