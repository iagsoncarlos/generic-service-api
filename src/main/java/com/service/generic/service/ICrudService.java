package com.service.generic.service;

import com.service.generic.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface ICrudService<T extends BaseDTO> {

    T find(UUID id);

    Page<T> findAll(Pageable pageable);

    T create(T dto);

    T update(UUID id, T dto);

    void delete(UUID id);
}