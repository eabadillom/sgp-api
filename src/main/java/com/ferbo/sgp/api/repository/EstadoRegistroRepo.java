package com.ferbo.sgp.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.EstadoRegistro;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface EstadoRegistroRepo extends CrudRepository<EstadoRegistro, Integer> {
	public abstract Optional<EstadoRegistro> findById(Integer id);
	public abstract Optional<EstadoRegistro> findByCodigo(String codigo);
        
        @Query("SELECT e FROM EstadoRegistro e WHERE e.activo = 1")
        @Override
        public abstract List<EstadoRegistro> findAll();
}
