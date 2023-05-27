package com.service.generic.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.generic.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsersDTO extends BaseDTO {

    @NotBlank
    public String name;

    @NotBlank
    public String username;

    @NotBlank
    public String email;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

}
