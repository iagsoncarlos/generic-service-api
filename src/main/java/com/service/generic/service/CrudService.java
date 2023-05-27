package com.service.generic.service;

import com.service.generic.dto.BaseDTO;
import com.service.generic.mapper.IGenericMapper;
import com.service.generic.model.BaseEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static java.lang.String.format;

@AllArgsConstructor
public abstract class CrudService<T extends BaseDTO, E extends BaseEntity> implements ICrudService<T> {

    protected final IGenericMapper<T, E> mapper;
    protected final PagingAndSortingRepository<E, UUID> repository;

    @Override
    @Transactional(readOnly = true)
    public T find(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(format("could not get entity with id: %s", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T create(T dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T update(UUID id, T dto) {
        find(id);
        E e = mapper.toEntity(dto);
        return mapper.toDto(repository.save(e));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        find(id);
        repository.deleteById(id);
    }

}
