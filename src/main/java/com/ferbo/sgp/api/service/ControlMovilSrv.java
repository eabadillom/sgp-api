package com.ferbo.sgp.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.model.ControlMovil;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.repository.ControlMovilRepo;

@Service
public class ControlMovilSrv {

    @Autowired
    private ControlMovilRepo controlMovilRepo;

    public List<ControlMovil> obtenerPorSistema(Sistema sistema) {
        return (List<ControlMovil>) controlMovilRepo.findBySistema(sistema);
    }

    public List<ControlMovil> obtenerPorSistemaYMenorFecha(Sistema sistema, Date fecha){
        return (List<ControlMovil>) controlMovilRepo.findBySistemaYMenorFecha(sistema, fecha);
    }

    public List<ControlMovil> obtenerPorSistemaYPeriodo(Sistema sistema, Date fechaIncio, Date fechaFin){
        return (List<ControlMovil>) controlMovilRepo.findBySistemaYPeriodo(sistema, fechaIncio, fechaFin);
    }

    public ControlMovil guardarToken(ControlMovil controlMovil) {
        return controlMovilRepo.save(controlMovil);
    }

}
