package pro.farmmanager.infrastructure;

import io.vavr.control.Option;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {

    void save(UUID uuid, T t);

    T save(T t);

    void saveAll(Iterable<T> t);

    Option<T> findById(UUID uuid);

    List<T> findAll();

    void deleteById(UUID uuid);

    void delete(T t);

    void update(T t);

    void update(UUID uuid, T t);

}
