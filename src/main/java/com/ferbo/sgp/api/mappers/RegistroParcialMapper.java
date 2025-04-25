package com.ferbo.sgp.api.mappers;

import com.ferbo.sgp.api.dto.RegistroParcialDTO;
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
public interface RegistroParcialMapper extends GenericMapper<RegistroParcialDTO, RegistroAsistencia>
{
    RegistroParcialMapper INSTANCE = Mappers.getMapper(RegistroParcialMapper.class);
    
    @Override
    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "fechaEntrada", source = "fechaEntrada"),
        @Mapping(target = "fechaSalida", source = "fechaSalida"),
        @Mapping(target = "codigo", source = "status.codigo"),
        @Mapping(target = "nombre", source = "empleado.nombre"),
        @Mapping(target = "primeroAp", source = "empleado.primeroAp"),
        @Mapping(target = "segundoAp", source = "empleado.segundoAp")
    })
    public RegistroParcialDTO toDTO(RegistroAsistencia entity);
    
    @Override
    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "fechaEntrada", source = "fechaEntrada"),
        @Mapping(target = "fechaSalida", source = "fechaSalida"),
        @Mapping(target = "status.codigo", source = "codigo"),
        @Mapping(target = "empleado.nombre", source = "nombre"),
        @Mapping(target = "empleado.primeroAp", source = "primeroAp"),
        @Mapping(target = "empleado.segundoAp", source = "segundoAp"),
    })
    public RegistroAsistencia toEntity(RegistroParcialDTO DTO);
    
    @Override
    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "fechaEntrada", source = "fechaEntrada"),
        @Mapping(target = "fechaSalida", source = "fechaSalida"),
        @Mapping(target = "codigo", source = "status.codigo"),
        @Mapping(target = "nombre", source = "empleado.nombre"),
        @Mapping(target = "primeroAp", source = "empleado.primeroAp"),
        @Mapping(target = "segundoAp", source = "empleado.segundoAp")
    })
    public List<RegistroParcialDTO> toDTOList(List<RegistroAsistencia> entityList);
    
    
    @Override
    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "fechaEntrada", source = "fechaEntrada"),
        @Mapping(target = "fechaSalida", source = "fechaSalida"),
        @Mapping(target = "status.codigo", source = "codigo"),
        @Mapping(target = "empleado.nombre", source = "nombre"),
        @Mapping(target = "empleado.primeroAp", source = "primeroAp"),
        @Mapping(target = "empleado.segundoAp", source = "segundoAp")
    })
    public List<RegistroAsistencia> toEntityList(List<RegistroParcialDTO> dtoList);
    
}
