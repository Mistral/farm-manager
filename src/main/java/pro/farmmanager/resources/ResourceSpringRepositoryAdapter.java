package pro.farmmanager.resources;

import io.vavr.control.Option;
import pro.farmmanager.infrastructure.SpringRepositoryAdapter;

class ResourceSpringRepositoryAdapter extends SpringRepositoryAdapter<Resource> implements ResourceRepository {

    private ResourceSpringRepository repository;

    ResourceSpringRepositoryAdapter(ResourceSpringRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Option<Resource> findByName(String name) {
        return Option.ofOptional(repository.findByName(name));
    }

}
