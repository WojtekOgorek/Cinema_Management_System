package ogorek.wojciech.domain.configs.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    List<T> findAll();

    List<T> findAllById(List<ID> ids);

    Optional<T> findById(ID id);

    Optional<T> findLast();

    Optional<T> add(T t);

    Optional<T> update(T t);

    Optional<T> delete(ID id);

    boolean deleteAll();
}
