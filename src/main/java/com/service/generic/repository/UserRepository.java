package com.service.generic.repository;

import com.service.generic.model.users.Users;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends IRepository<Users, UUID> {
}
