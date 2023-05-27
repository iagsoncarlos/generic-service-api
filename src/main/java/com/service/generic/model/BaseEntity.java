package com.service.generic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(unique = true)
    public UUID id;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    public LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {

        if (id == null) {
            id = UUID.randomUUID();
        }

    }

}