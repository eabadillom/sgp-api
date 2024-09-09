package com.ferbo.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.model.InformacionEmpresa;
import com.ferbo.sgp.api.repository.InformacionEmpresaRepo;

@Service
public class InformacionEmpresaSrv {
	
	Logger log = LogManager.getLogger(InformacionEmpresaSrv.class);
	
	@Autowired
	InformacionEmpresaRepo informacionEmpresaRepo;
	
	public InformacionEmpresa buscarPorId(Integer id) {
		InformacionEmpresa informacionEmpresa = null;
		Optional<InformacionEmpresa> oInformacionEmpresa = null;
		
		oInformacionEmpresa = informacionEmpresaRepo.findById(id);
		
		if(oInformacionEmpresa.isPresent())
			informacionEmpresa = oInformacionEmpresa.get();
		
		return informacionEmpresa;
	}
	
	public List<InformacionEmpresa> buscarPorIdPlanta(Integer idPlanta) {
		List<InformacionEmpresa> lista = null;
		lista = informacionEmpresaRepo.findByIdPlanta(idPlanta);
		
		for(InformacionEmpresa model : lista) {
			log.info("Biometrico: {}", model.getEmpleado().getBiometrico().getIdBiometrico());
		}
		
		return lista;
	}
}
