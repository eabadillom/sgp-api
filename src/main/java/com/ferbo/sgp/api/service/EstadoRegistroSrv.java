package com.ferbo.sgp.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.model.EstadoRegistro;
import com.ferbo.sgp.api.repository.EstadoRegistroRepo;

@Service
public class EstadoRegistroSrv {
	@Autowired
	private EstadoRegistroRepo estadoRegistroRepo;
	
	@Query("SELECT e FROM EstadoRegistro e WHERE e.codiog = :codigo")
	public Optional<EstadoRegistro> buscarPorCodigo(String codigo) {
		return estadoRegistroRepo.findByCodigo(codigo);
	}
}
