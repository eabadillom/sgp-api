package com.ferbo.sgp.api.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.models.DetEmpleadoModel;
import com.ferbo.sgp.api.repositories.DetEmpleadoRepository;

@Service
public class DetEmpleadoService {
    
    @Autowired
    DetEmpleadoRepository detEmpleadoRepository;

    public ArrayList<DetEmpleadoModel> obtenerEmpleados(){
        return (ArrayList<DetEmpleadoModel>) detEmpleadoRepository.findAll();
    }

    public DetEmpleadoModel obtenerPorNumEmpleado(String numEmpleado ){
        return (DetEmpleadoModel) detEmpleadoRepository.findByNumEmpleado(numEmpleado);
    } 


}
