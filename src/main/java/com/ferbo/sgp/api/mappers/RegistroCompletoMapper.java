package com.ferbo.sgp.api.mappers;

import com.ferbo.sgp.api.dto.RegistroCompletoDTO;
import com.ferbo.sgp.api.model.RegistroAsistencia;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author alberto
 */
@Mapper(componentModel = "spring")
public interface RegistroCompletoMapper extends GenericMapper<RegistroCompletoDTO, RegistroAsistencia>
{
    RegistroCompletoMapper INSTANCE = Mappers.getMapper(RegistroCompletoMapper.class);
    
    @Override
    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "fechaEntrada", source = "fechaEntrada"),
        @Mapping(target = "fechaSalida", source = "fechaSalida"),
        @Mapping(target = "codigo", source = "status.codigo"),
        @Mapping(target = "nombre", source = "empleado.nombre"),
        @Mapping(target = "primeroAp", source = "empleado.primeroAp"),
        @Mapping(target = "segundoAp", source = "empleado.segundoAp"),
        @Mapping(target = "planta", source = "empleado.informacionEmpresa.planta.descripcion")
    })
    public RegistroCompletoDTO toDTO(RegistroAsistencia entity);
    
    @Override
    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "fechaEntrada", source = "fechaEntrada"),
        @Mapping(target = "fechaSalida", source = "fechaSalida"),
        @Mapping(target = "status.codigo", source = "codigo"),
        @Mapping(target = "empleado.nombre", source = "nombre"),
        @Mapping(target = "empleado.primeroAp", source = "primeroAp"),
        @Mapping(target = "empleado.segundoAp", source = "segundoAp"),
        @Mapping(target = "empleado.informacionEmpresa.planta.descripcion", source = "planta")
    })
    public RegistroAsistencia toEntity(RegistroCompletoDTO DTO);
    
    @Override
    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "fechaEntrada", source = "fechaEntrada"),
        @Mapping(target = "fechaSalida", source = "fechaSalida"),
        @Mapping(target = "codigo", source = "status.codigo"),
        @Mapping(target = "nombre", source = "empleado.nombre"),
        @Mapping(target = "primeroAp", source = "empleado.primeroAp"),
        @Mapping(target = "segundoAp", source = "empleado.segundoAp"),
        @Mapping(target = "planta", source = "empleado.informacionEmpresa.planta.descripcion")
    })
    public List<RegistroCompletoDTO> toDTOList(List<RegistroAsistencia> entityList);
    
    @Override
    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "fechaEntrada", source = "fechaEntrada"),
        @Mapping(target = "fechaSalida", source = "fechaSalida"),
        @Mapping(target = "status.codigo", source = "codigo"),
        @Mapping(target = "empleado.nombre", source = "nombre"),
        @Mapping(target = "empleado.primeroAp", source = "primeroAp"),
        @Mapping(target = "empleado.segundoAp", source = "segundoAp"),
        @Mapping(target = "empleado.informacionEmpresa.planta.descripcion", source = "planta")
    })
    public List<RegistroAsistencia> toEntityList(List<RegistroCompletoDTO> dtoList);
    
}
