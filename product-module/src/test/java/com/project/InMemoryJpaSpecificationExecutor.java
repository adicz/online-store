package com.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.FluentQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryJpaSpecificationExecutor<T, ID extends Serializable> extends InMemoryJpaRepository<T, ID> implements JpaSpecificationExecutor<T> {

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        final List<T> results = findAll(spec);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return findAll(spec, Sort.unsorted());
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        final List<T> allResults = findAll(spec);
        final int totalCount = allResults.size();
        final List<T> paginatedResults = getPageFromList(allResults, pageable);
        return new PageImpl<>(paginatedResults, pageable, totalCount);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        throw new UnsupportedOperationException("findAll is not supported in InMemoryJpaRepository");
    }

    @Override
    public long count(Specification<T> spec) {
        return findAll(spec).size();
    }

    @Override
    public boolean exists(Specification<T> spec) {
        return !findAll(spec).isEmpty();
    }

    @Override
    public long delete(Specification<T> spec) {
        final List<T> entitiesToDelete = findAll(spec);
        deleteAll(entitiesToDelete);
        return entitiesToDelete.size();
    }

    @Override
    public <S extends T, R> R findBy(Specification<T> spec, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("findBy is not supported in InMemoryJpaSpecificationExecutor");
    }
}
