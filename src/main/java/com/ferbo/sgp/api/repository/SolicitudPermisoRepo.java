package com.ferbo.sgp.api.repository;

import com.ferbo.sgp.api.model.SolicitudPermiso;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author alberto
 */
public interface SolicitudPermisoRepo extends CrudRepository<SolicitudPermiso, Integer>
{
    @Query("SELECT sp FROM SolicitudPermiso sp")
    public abstract List<SolicitudPermiso> buscarTodos();
    
    @Query("SELECT sp FROM SolicitudPermiso sp WHERE sp.empleadoSol.idEmpleado = :idEmpleado ORDER BY sp.fechaCap")
    public abstract List<SolicitudPermiso> buscarPorEmp(Integer idEmpleado);
    
    @Query("SELECT sp FROM SolicitudPermiso sp WHERE sp.estatusSolicitud.clave = :clave ORDER BY sp.fechaCap")
    public abstract List<SolicitudPermiso> buscarPorClave(String clave);
    
    @Query("SELECT sp FROM SolicitudPermiso sp WHERE sp.tipoSolicitud.clave = :clave ORDER BY sp.fechaCap")
    public abstract List<SolicitudPermiso> buscarPorTipoSolicitud(String clave);
    
    @Query("SELECT sp FROM SolicitudPermiso sp WHERE sp.empleadoSol.idEmpleado = :idEmpleado AND sp.estatusSolicitud.clave = :clave")
    public abstract List<SolicitudPermiso> buscarPorEmpClave(Integer idEmpleado, String clave);
    
    @Query("SELECT sp FROM SolicitudPermiso sp WHERE sp.empleadoSol.idEmpleado = :idEmpleado AND sp.tipoSolicitud.clave = :clave")
    public abstract List<SolicitudPermiso> buscarPorEmpTipoSolicitud(Integer idEmpleado, String clave);
    
    @Query("SELECT sp FROM SolicitudPermiso sp WHERE sp.empleadoSol.idEmpleado = :idEmpleado AND ((:fechaIni = sp.fechaInicio AND :fechaFin = sp.fechaFin) OR ((:fechaIni = sp.fechaFin) OR (:fechaFin = sp.fechaInicio)) OR ((:fechaIni BETWEEN sp.fechaInicio AND sp.fechaFin) OR (:fechaFin BETWEEN sp.fechaInicio AND sp.fechaFin)) OR ((sp.fechaInicio BETWEEN :fechaIni AND :fechaFin) OR (sp.fechaFin BETWEEN :fechaIni AND :fechaFin))) AND (sp.estatusSolicitud.clave = :enviada OR sp.estatusSolicitud.clave = :aprobada)")
    public abstract List<SolicitudPermiso> buscarPorEmpPeriodo(Integer idEmpleado, OffsetDateTime fechaIni, OffsetDateTime fechaFin, String enviada, String aprobada);
    
}
