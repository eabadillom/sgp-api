
package com.ferbo.sgp.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Biometrico;

public interface BiometricoRepo extends CrudRepository<Biometrico,Integer> {
    @Query("SELECT b FROM Biometrico b WHERE b.empleado.numeroEmpleado = :numeroEmpleado")
    public abstract Biometrico findByNumeroEmpleado(String numeroEmpleado);
    
    @Query("SELECT b FROM Biometrico b "
    		+ "INNER JOIN Empleado e "
    		+ "INNER JOIN InformacionEmpresa ie "
    		+ "INNER JOIN Planta p "
    		+ "WHERE p.id = :idPlanta")
    public abstract List<Biometrico> findByIdPlanta(Integer idPlanta);
}