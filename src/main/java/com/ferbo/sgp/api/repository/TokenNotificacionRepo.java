package com.ferbo.sgp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.model.TokenNotificacion;

public interface TokenNotificacionRepo extends JpaRepository <TokenNotificacion, Integer> {

    @Query("SELECT tn FROM TokenNotificacion tn WHERE tn.sistema = :sistema AND tn.esValido = TRUE")
    public abstract TokenNotificacion findBySistemaYActivo(Sistema sistema);

    @Query("SELECT tn FROM TokenNotificacion tn WHERE tn.esValido = TRUE")
    public abstract List<TokenNotificacion> findAllByActivo();
}
