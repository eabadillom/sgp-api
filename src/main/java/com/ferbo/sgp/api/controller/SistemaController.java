package com.ferbo.sgp.api.controller;

import com.ferbo.sgp.api.dto.SistemaDTO;
import com.ferbo.sgp.api.service.SistemaSrv;
import com.ferbo.sgp.api.tool.ErrorResponseBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movil")
public class SistemaController 
{
    private static Logger log = LogManager.getLogger(SistemaController.class);
    
    private static final String TIPO_ERROR_SISTEMA = "Sistema";
    
    @Autowired
    private SistemaSrv sistemaSrv;
    
    @GetMapping(value = "/sistema/{username}", produces = "application/json")
    public ResponseEntity<?> obtenerSistemaPorNombre(@PathVariable String username) 
    {
        SistemaDTO sistemaDTO = null;
        try {
            log.info("Inicio proceso para obtener el sistema en base a los parametros dados.");
            sistemaDTO = sistemaSrv.buscarDtoPorNombre(username);
            log.info("Finaliza proceso para obtener el sistema en base a los parametros dados.");
        }catch (RuntimeException ex) {
            log.warn("Problema al obtener el sistema en base a los parametros dados. {}", ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_SISTEMA, ex);
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener el sistema. {}", ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_SISTEMA, ex);
        }
        
        return ResponseEntity.ok(sistemaDTO);
    }
}
