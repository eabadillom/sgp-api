package com.ferbo.sgp.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.EstadoRegistro;

public interface EstadoRegistroRepo extends CrudRepository<EstadoRegistro, Integer> {
	public abstract Optional<EstadoRegistro> findById(Integer id);
	public abstract Optional<EstadoRegistro> findByCodigo(String codigo);
}
