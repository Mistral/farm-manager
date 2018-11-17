package pro.farmmanager.farmlands;

import pro.farmmanager.infrastructure.InMemoryRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class FarmlandInMemoryRepository extends InMemoryRepository<Farmland> implements FarmlandRepository {

    @Override
    public List<Farmland> findByOwnerId(UUID ownerId) {
        return findAll()
            .stream()
            .filter(f -> f.getOwnerId().equals(ownerId))
            .collect(Collectors.toList());
    }

}
