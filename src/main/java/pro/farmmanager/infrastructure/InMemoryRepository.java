package pro.farmmanager.infrastructure;

import io.vavr.control.Option;
import pro.farmmanager.shared_kernel.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository<T extends BaseEntity> implements Repository<T> {

    final protected ConcurrentHashMap<UUID, T> map = new ConcurrentHashMap<>();

    @Override
    final public void save(UUID uuid, T t) {
        map.put(uuid, t);
    }

    @Override
    final public T save(T t) {
        map.put(t.getId(), t);
        return t;
    }

    @Override
    final public void saveAll(Iterable<T> t) {
        t.forEach(this::save);
    }

    @Override
    final public Option<T> findById(UUID uuid) {
        return Option.of(map.get(uuid));
    }

    @Override
    final public List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    final public void deleteById(UUID uuid) {
        map.remove(uuid);
    }

    @Override
    final public void delete(T t) {
        map.remove(t.getId());
    }

    @Override
    final public void update(T t) {
        update(t.getId(), t);
    }

    @Override
    final public void update(UUID uuid, T t) {
        map.put(uuid, t);
    }

}
