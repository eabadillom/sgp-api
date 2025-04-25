package com.ferbo.sgp.api.mappers;

import java.util.List;

/**
 *
 * @author alberto
 */
public interface GenericMapper<D, E> 
{
    D toDTO(E entity);
    
    E toEntity(D DTO);
    
    List<D> toDTOList(List<E> entityList);
    
    List<E> toEntityList(List<D> dtoList);
    
}
