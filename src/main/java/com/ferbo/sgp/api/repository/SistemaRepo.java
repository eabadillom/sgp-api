package com.ferbo.sgp.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Sistema;

public interface SistemaRepo extends CrudRepository<Sistema, Integer> {
	public abstract Optional<Sistema> findById(Integer id);
	public abstract Sistema findByNombre(String nombre);
}
