package com.service.generic.mapper.users;

import com.service.generic.dto.users.UsersDTO;
import com.service.generic.mapper.IMapper;
import com.service.generic.model.users.Users;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IMapper {
    @Override
    public void addMapping(MapperFactory mapperFactory) {
        mapperFactory.classMap(Users.class, UsersDTO.class)
                .byDefault()
                .register();
    }
}
