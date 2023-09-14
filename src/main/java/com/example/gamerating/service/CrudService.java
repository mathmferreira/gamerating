package com.example.gamerating.service;

import com.example.gamerating.common.UpdaterExample;
import com.example.gamerating.domain.model.AbstractEntity;
import com.example.gamerating.repository.EntityRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

public abstract class CrudService<T extends AbstractEntity> {

    protected abstract EntityRepository<T> getRepository();

    @Transactional
    public T create(T entity) {
        if (!entity.isNew() && findById(entity.getId()).isPresent()) {
            throw new EntityExistsException();
        }
        return getRepository().save(entity);
    }

    public Optional<T> findById(Long id) {
        return getRepository().findById(id);
    }

    public Page<T> findAll(T filters, Pageable pageable) {
        Example<T> example = Example.of(filters, defaultExampleMatcher());
        return getRepository().findAll(example, pageable);
    }

    @Transactional
    public T update(Long id, T toUpdate) {
        T entity = findById(id).orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(toUpdate, entity, "id");
        return getRepository().save(entity);
    }

    @Transactional
    public T update(Long id, UpdaterExample<T> toUpdate) {
        T entity = findById(id).orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(toUpdate.getProbe(), entity, toUpdate.getIgnoredPaths());
        return getRepository().save(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        T entity = findById(id).orElseThrow(EntityNotFoundException::new);
        getRepository().deleteById(Objects.requireNonNull(entity.getId()));
    }

    protected ExampleMatcher defaultExampleMatcher() {
        return ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();
    }

}
