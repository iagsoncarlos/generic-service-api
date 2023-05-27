package com.service.generic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO implements Serializable {

    public UUID id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String updatedAt ;

}
