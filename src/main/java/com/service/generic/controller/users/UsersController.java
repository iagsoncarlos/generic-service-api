package com.service.generic.controller.users;

import com.service.generic.controller.CrudController;
import com.service.generic.dto.users.UsersDTO;
import com.service.generic.service.ICrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@Tag(name = "Users Controller")
public class UsersController extends CrudController<UsersDTO> {

    public UsersController(ICrudService<UsersDTO> service) {
        super(service);
    }

}
