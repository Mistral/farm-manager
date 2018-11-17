package pro.farmmanager.infrastructure;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SpringRepositoryAdapter<T> implements Repository<T> {

    protected CrudRepository<T, UUID> repository;

    public SpringRepositoryAdapter(CrudRepository<T, UUID> repository) {
        this.repository = repository;
    }

    @Override
    final public void save(UUID uuid, T entity) {
        repository.save(entity);
    }

    @Override
    final public T save(T t) {
        return repository.save(t);
    }

    @Override
    final public void saveAll(Iterable<T> t) {
        repository.saveAll(t);
    }

    @Override
    final public Optional<T> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    final public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    final public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    final public void delete(T t) {
        repository.delete(t);
    }

    @Override
    final public void update(T t) {
        repository.save(t);
    }

    @Override
    final public void update(UUID uuid, T t) {
        findById(uuid).ifPresent(this::update);
    }

}
