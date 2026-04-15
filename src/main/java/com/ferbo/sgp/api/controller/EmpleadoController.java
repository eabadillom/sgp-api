package com.ferbo.sgp.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ferbo.sgp.api.dto.EmpleadoDTO;
import com.ferbo.sgp.api.service.EmpleadoSrv;
import com.ferbo.sgp.api.tool.ErrorResponseBuilder;

@RestController
@RequestMapping("movil")
public class EmpleadoController 
{
    private static Logger log = LogManager.getLogger(EmpleadoController.class);
    
    private static final String TIPO_ERROR_EMPLEADO = "Empleado";
    
    @Autowired
    private EmpleadoSrv empleadoSrv;
    
    @GetMapping(value = "/empleado/{numero}", produces = "application/json")
    public ResponseEntity<?> obtenerEmpleadoPorNumero(@PathVariable String numero) 
    {
        EmpleadoDTO empleadoDTO = null;
        try {
            log.info("Inicio proceso para obtener al empleado en base a los parametros dados.");
            empleadoDTO = empleadoSrv.buscarPorNumero(numero);
            log.info("Finaliza proceso para obtener al empleado en base a los parametros dados.");
        } catch (RuntimeException ex) {
            log.warn("Problema al obtener al empleado en base a los parametros dados. {}", ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_EMPLEADO, ex);
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener al empleado. {}", ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_EMPLEADO, ex);
        }
        return ResponseEntity.ok(empleadoDTO);
    }
    
}
