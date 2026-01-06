package com.ferbo.sgp.api.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.RegistroAsistencia;

public interface RegistroAsistenciaRepo extends CrudRepository<RegistroAsistencia, Integer>{
	
	@Query("SELECT r FROM RegistroAsistencia r WHERE r.empleado.numeroEmpleado = :numeroEmpleado AND r.fechaEntrada BETWEEN :fechaInicio AND :fechaFin")
	public abstract Optional<RegistroAsistencia> buscarPorPeriodo(String numeroEmpleado, OffsetDateTime fechaInicio, OffsetDateTime fechaFin);
        
        @Query("SELECT r FROM RegistroAsistencia r WHERE r.empleado.idEmpleado = :idEmp AND (r.fechaEntrada = :fechaInicio AND r.fechaSalida = :fechaFin) AND r.status.codigo = :codigo")
        public abstract Optional<RegistroAsistencia> buscarPorPeriodoAusencia(Integer idEmp, OffsetDateTime fechaInicio, OffsetDateTime fechaFin, String codigo);
	
	@Query("SELECT r FROM RegistroAsistencia r WHERE r.status.codigo = :codigo AND r.fechaEntrada BETWEEN :fechaInicio AND :fechaFin")
	public abstract List<RegistroAsistencia> buscarPorPeriodoYEstatus(String codigo, OffsetDateTime fechaInicio, OffsetDateTime fechaFin);
	
        @Query("SELECT r FROM RegistroAsistencia r WHERE r.empleado.idEmpleado = :idEmp AND r.status.codigo = :codigo AND ((r.fechaEntrada BETWEEN :fechaInicial AND :fechaFinal) OR (r.fechaEntrada = :fechaInicial) OR (r.fechaEntrada = :fechaFinal))")
        public abstract List<RegistroAsistencia> buscarPorPeriodoSolicitud(Integer idEmp, String codigo, OffsetDateTime fechaInicial, OffsetDateTime fechaFinal);
        
        @Query("SELECT r FROM RegistroAsistencia r WHERE r.empleado.idEmpleado = :idEmp AND (:fecha BETWEEN r.fechaEntrada AND r.fechaSalida) AND r.status.codigo = :codigo")
        public abstract Optional<RegistroAsistencia> buscarPorFechaEstatus(Integer idEmp, OffsetDateTime fecha, String codigo);
}
