package com.service.generic.controller;

import com.service.generic.dto.BaseDTO;
import com.service.generic.service.ICrudService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;

@AllArgsConstructor
public abstract class CrudController <T extends BaseDTO>{

    protected final ICrudService<T> service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<T> find(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(service.find(UUID.fromString(id)));
    }

    @GetMapping
    public ResponseEntity<Page<T>> findAll(@Nullable Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<T> create(@Valid @RequestBody T dto) {
        return new ResponseEntity<T>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<T> update(@PathVariable(value = "id") String id, @Valid @RequestBody T dto) {
        return ResponseEntity.ok(service.update(UUID.fromString(id), dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        service.delete(UUID.fromString(id));
        return noContent().build();
    }

}
