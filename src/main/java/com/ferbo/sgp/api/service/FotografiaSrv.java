package com.ferbo.sgp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.model.Fotografia;
import com.ferbo.sgp.api.repository.FotografiaRepo;

@Service
public class FotografiaSrv {
	
	@Autowired
	FotografiaRepo fotografiaRepository;
	
	public Fotografia buscarPorNumeroEmpleado(String numeroEmpleado) {
		return (Fotografia) fotografiaRepository.findByNumeroEmpleado(numeroEmpleado);
	}
}
