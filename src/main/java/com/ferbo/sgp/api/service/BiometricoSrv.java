package com.ferbo.sgp.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.repository.BiometricoRepo;
import com.ferbo.sgp.api.model.Biometrico;

@Service
public class BiometricoSrv {
	
    @Autowired
    BiometricoRepo biometricoRepo;

    public ArrayList<Biometrico> obtenerBiometricos(){
        return (ArrayList<Biometrico>) biometricoRepo.findAll();
    }

    public Optional<Biometrico> obtenerPorId(Integer id){
        return biometricoRepo.findById(id);
    }
    
    public Biometrico buscarPorNumeroEmpleado(String numero) {
    	Biometrico biometrico = null;
    	biometrico = biometricoRepo.findByNumeroEmpleado(numero);
    	return biometrico;
    }

	public List<Biometrico> buscarPorIdPlanta(Integer idPlanta) {
		List<Biometrico> listaBiometricos = null;
		listaBiometricos = biometricoRepo.findByIdPlanta(idPlanta);
		
		return listaBiometricos;
	}

}
