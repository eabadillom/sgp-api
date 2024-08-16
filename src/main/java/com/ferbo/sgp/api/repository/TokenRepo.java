package com.ferbo.sgp.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ferbo.sgp.api.model.Token;

public interface TokenRepo extends CrudRepository<Token, Integer> {
	
	@Query("SELECT t FROM Token t WHERE t.caducidad BETWEEN :inicio AND :fin AND t.valido = :activo")
	public abstract List<Token> findByVigenteActivo(LocalDateTime inicio, LocalDateTime fin, Boolean activo);

	@Query("SELECT t FROM Token t WHERE t.caducidad < :fecha AND t.valido = false")
	public abstract List<Token> findByNoVigentes(LocalDateTime fecha);
	
}
