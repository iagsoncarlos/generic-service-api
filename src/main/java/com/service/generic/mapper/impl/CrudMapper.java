package com.service.generic.mapper.impl;

import com.service.generic.dto.BaseDTO;
import com.service.generic.mapper.IGenericMapper;
import com.service.generic.mapper.IMapper;
import com.service.generic.model.BaseEntity;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CrudMapper<T extends BaseDTO, E extends BaseEntity> implements IGenericMapper<T, E> {

    protected MapperFacade mapper = null;

    protected final List<IMapper> mappers;

    protected CrudMapper(List<IMapper> mappers) {
        this.mappers = mappers;
    }

    @PostConstruct
    protected void init() {
        if (mapper == null) {
            final var mapperFactory = new DefaultMapperFactory.Builder().build();
            mappers.forEach(mappingSupplier -> mappingSupplier.addMapping(mapperFactory));
            this.mapper = mapperFactory.getMapperFacade();
        }
    }

    @Override
    public E toEntity(T dto) {
        return (E) mapper.map(dto, BaseEntity.class);
    }

    @Override
    public T toDto(E entity) {
        return (T) mapper.map(entity, BaseDTO.class);
    }
}
