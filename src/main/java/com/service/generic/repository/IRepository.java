package com.service.generic.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface IRepository<T, I> extends PagingAndSortingRepository<T, I> {
}
