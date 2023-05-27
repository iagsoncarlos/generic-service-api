package com.service.generic.mapper;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericMapper<T, E> {

    E toEntity(T dto);

    T toDto(E entity);
}
