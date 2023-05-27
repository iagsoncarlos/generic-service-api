package com.service.generic.model.users;

import com.service.generic.model.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity {

    @NotBlank
    @ToString.Include
    @Column(name = "name", nullable = false)
    public String name;

    @NotBlank
    @ToString.Include
    @Column(name = "username", unique = true, nullable = false)
    public String username;

    @NotBlank
    @ToString.Include
    @Column(name = "email", unique = true, nullable = false)
    public String email;

    @NotBlank
    @ToString.Include
    @Column(name = "password", nullable = false)
    public String password;

}