package com.service.generic.service.users.impl;

import com.service.generic.dto.users.UsersDTO;
import com.service.generic.mapper.IGenericMapper;
import com.service.generic.model.users.Users;
import com.service.generic.repository.UserRepository;
import com.service.generic.service.CrudService;
import com.service.generic.service.users.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static java.lang.String.format;

@Component
public class UsersService extends CrudService<UsersDTO, Users> implements IUsersService {

    @Autowired
    private UserRepository userRepository;

    public UsersService(IGenericMapper<UsersDTO, Users> mapper, PagingAndSortingRepository<Users, UUID> repository) {
        super(mapper, repository);
    }

    @Override
    @Transactional(readOnly = true)
    public UsersDTO find(UUID id) {
        return userRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(format("could not get entity with id: %s", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UsersDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UsersDTO create(UsersDTO dto) {
        return mapper.toDto(userRepository.save(mapper.toEntity(dto)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UsersDTO update(UUID id, UsersDTO dto) {
        this.find(id);
        return mapper.toDto(userRepository.save(mapper.toEntity(dto)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {
        this.find(id);
        userRepository.deleteById(id);
    }

}
