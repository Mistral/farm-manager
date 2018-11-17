package pro.farmmanager.farmlands;

import pro.farmmanager.infrastructure.SpringRepositoryAdapter;

import java.util.List;
import java.util.UUID;

class FarmlandSpringRepositoryAdapter extends SpringRepositoryAdapter<Farmland> implements FarmlandRepository {

    private SpringFarmlandRepository repository;

    FarmlandSpringRepositoryAdapter(SpringFarmlandRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Farmland> findByOwnerId(UUID ownerId) {
        return repository.findByOwnerId(ownerId);
    }

}
