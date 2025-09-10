package com.ferbo.sgp.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.repository.EmpleadoRepo;

@Service
public class EmpleadoSrv {
    
    @Autowired
    EmpleadoRepo detEmpleadoRepository;

    public List<Empleado> obtenerEmpleados(){
        return (List<Empleado>) detEmpleadoRepository.findAll();
    }

    public Empleado buscarPorNumeroEmpleado(String numeroEmpleado ){
        return (Empleado) detEmpleadoRepository.findByNumeroEmpleado(numeroEmpleado)
            .orElseThrow(() -> new RuntimeException("No se encontro registro de empleado con ese indentificador"));
    }
    
    public List<Empleado> obtenerEmpleadosActivos() {
    	return (List<Empleado>) detEmpleadoRepository.findByActivo();
    }
    
}
