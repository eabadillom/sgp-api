package com.ferbo.sgp.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.repository.EmpleadoRepo;

@Service
public class EmpleadoSrv {
    
    @Autowired
    EmpleadoRepo detEmpleadoRepository;

    public ArrayList<Empleado> obtenerEmpleados(){
        return (ArrayList<Empleado>) detEmpleadoRepository.findAll();
    }

    public Empleado buscarPorNumeroEmpleado(String numeroEmpleado ){
        return (Empleado) detEmpleadoRepository.findByNumeroEmpleado(numeroEmpleado);
    } 


}
