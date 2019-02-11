package pro.farmmanager.resources;

import io.vavr.control.Option;
import pro.farmmanager.infrastructure.InMemoryRepository;

class ResourceInMemoryRepository extends InMemoryRepository<Resource> implements ResourceRepository {

    @Override
    public Option<Resource> findByName(String name) {
        return Option.ofOptional(findAll().stream()
                                          .filter(r -> r.getName().equals(name))
                                          .findAny());
    }

}
