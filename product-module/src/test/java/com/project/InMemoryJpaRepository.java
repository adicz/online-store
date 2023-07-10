package com.project;

import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.junit.platform.commons.util.ReflectionUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryJpaRepository<T, ID extends Serializable> implements JpaRepository<T, ID> {

    protected final Map<ID, T> map = new HashMap<>();

    @Override
    public void flush() {
        // Nie jest wymagane dla HashMapy
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        save(entity);
        return entity;
    }

    @Override
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        final List<S> savedEntities = saveAll(entities);
        flush();
        return savedEntities;
    }

    @Override
    public void deleteAllInBatch(Iterable<T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<ID> ids) {
        for (ID id : ids) {
            deleteById(id);
        }
    }

    @Override
    public void deleteAllInBatch() {
        map.clear();
    }

    @Override
    public T getOne(ID id) {
        return map.get(id);
    }

    @Override
    public T getById(ID id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public T getReferenceById(ID id) {
        return getById(id);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return findAll(example).stream().findFirst();
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return findAll(example, Sort.unsorted());
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("findAll is not supported in InMemoryJpaRepository");
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        final List<S> allResults = findAll(example);
        final int totalCount = allResults.size();
        final List<S> paginatedResults = getPageFromList(allResults, pageable);
        return new PageImpl<>(paginatedResults, pageable, totalCount);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return findAll(example).size();
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return !findAll(example).isEmpty();
    }

    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("findBy is not supported in InMemoryJpaRepository");
    }

    @Override
    public <S extends T> S save(S entity) {
        final ID id = getEntityId(entity);
        map.put(id, entity);
        setEntityId(entity, id);
        return entity;
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        final List<S> savedEntities = new ArrayList<>();
        for (S entity : entities) {
            savedEntities.add(save(entity));
        }
        return savedEntities;
    }

    @Override
    public Optional<T> findById(ID id) {
        final T entity = map.get(id);
        return Optional.ofNullable(entity);
    }

    @Override
    public boolean existsById(ID id) {
        return map.containsKey(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        final List<T> resultList = new ArrayList<>();
        for (ID id : ids) {
            findById(id).ifPresent(resultList::add);
        }
        return resultList;
    }

    @Override
    public long count() {
        return map.size();
    }

    @Override
    public void deleteById(ID id) {
        map.remove(id);
    }

    @Override
    public void delete(T entity) {
        final ID id = getEntityId(entity);
        deleteById(id);
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        for (ID id : ids) {
            deleteById(id);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        map.clear();
    }

    @Override
    public List<T> findAll(Sort sort) {
        throw new UnsupportedOperationException("findAll is not supported in InMemoryJpaRepository");
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        final List<T> allResults = new ArrayList<>(map.values());
        final int totalCount = allResults.size();
        final List<T> paginatedResults = getPageFromList(allResults, pageable);
        return new PageImpl<>(paginatedResults, pageable, totalCount);
    }

    // Metoda pomocnicza do pobierania identyfikatora encji
    @SuppressWarnings("unchecked")
    private ID getEntityId(T entity) {
        final Long nextId = map.keySet().stream()
                .mapToLong(key -> (Long) key)
                .max()
                .orElse(0L) + 1;
        return (ID) nextId;
    }

    @SneakyThrows
    private void setEntityId(T entity, ID id) {
        final Class<?> entityClass = entity.getClass();
        final Optional<Method> setIdMethod = ReflectionUtils.findMethod(entityClass, "setId", id.getClass());
        if (setIdMethod.isPresent()) {
            ReflectionUtils.makeAccessible(setIdMethod.get());
            ReflectionUtils.invokeMethod(setIdMethod.get(), entity, id);
        }
    }

    // Metoda pomocnicza do sortowania listy wynikowej
    protected <S> void sortResults(List<S> results, Comparator<? super S> comparator) {
        if (comparator != null) {
            results.sort(comparator);
        }
    }

    // Metoda pomocnicza do paginacji listy wynikowej
    <S> List<S> getPageFromList(List<S> list, Pageable pageable) {
        final int pageSize = pageable.getPageSize();
        final int pageNumber = pageable.getPageNumber();
        final int fromIndex = Math.min(pageNumber * pageSize, list.size());
        final int toIndex = Math.min((pageNumber + 1) * pageSize, list.size());
        return list.subList(fromIndex, toIndex);
    }
}
