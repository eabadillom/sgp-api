package com.ferbo.sgp.api.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.models.DetBiometricoModel;
import com.ferbo.sgp.api.repositories.DetBiometricoRepository;

@Service
public class DetBiometricoService {
    @Autowired
    DetBiometricoRepository detBiometricoRepository;

    public ArrayList<DetBiometricoModel> obtenerBiometricos(){
        return (ArrayList<DetBiometricoModel>) detBiometricoRepository.findAll();
    }

    public Optional<DetBiometricoModel> obtenerPorId(Integer id){
        return detBiometricoRepository.findById(id);
    }

    public DetBiometricoModel obtenerNoEmpleado(Integer idEmpleado){
        return (DetBiometricoModel) detBiometricoRepository.findByIdEmpleado(idEmpleado);
    }

}
