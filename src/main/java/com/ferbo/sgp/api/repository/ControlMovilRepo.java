package com.ferbo.sgp.api.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ferbo.sgp.api.model.ControlMovil;
import com.ferbo.sgp.api.model.Sistema;

public interface ControlMovilRepo extends JpaRepository <ControlMovil, Integer> {

    public abstract ControlMovil findTopBySistemaOrderByIdDesc(Sistema sistema);

    @Query("SELECT cm FROM ControlMovil cm WHERE cm.sistema = :sistema and cm.expiracion <= :fecha")
    public abstract List<ControlMovil> findBySistemaYMenorFecha(Sistema sistema, Date fecha);

    @Query("SELECT cm FROM ControlMovil cm WHERE cm.sistema = :sistema and (cm.expiracion >= :fechaInicio and cm.expiracion <= :fechaFin)")
    public abstract List<ControlMovil> findBySistemaYPeriodo(Sistema sistema, Date fechaInicio, Date fechaFin);

    @Query("SELECT cm FROM ControlMovil cm WHERE cm.token = :token")
    public abstract Optional<ControlMovil> findByToken(String token);
}
