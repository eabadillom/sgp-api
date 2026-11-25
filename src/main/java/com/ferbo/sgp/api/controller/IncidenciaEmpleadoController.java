package com.ferbo.sgp.api.controller;

import java.util.List;
import com.ferbo.sgp.api.dto.EmpleadoAsisDTO;
import com.ferbo.sgp.api.service.IncidenciaEmpleadoSrv;
import com.ferbo.sgp.api.tool.ErrorResponseBuilder;
import com.ferbo.tools.exception.RuleException;
import com.ferbo.tools.exception.SystemException;
import com.ferbo.tools.exception.ToolException;
import com.ferbo.tools.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movil")
public class IncidenciaEmpleadoController 
{
    private static Logger log = LogManager.getLogger(IncidenciaEmpleadoController.class);
    
    @Autowired
    private IncidenciaEmpleadoSrv incidenciaEmpleadoSrv;
    
    @GetMapping("incidencias/asistencia/empleados")
    public ResponseEntity<?> listarEmpleados(@RequestParam(required = true) String fechaInicio, @RequestParam(required = true) String fechaFin, 
        @RequestParam(required = false) Integer idEmpresa, @RequestParam(required = false) Integer idPlanta) 
    {
        List<EmpleadoAsisDTO> listEmpleado = null;
        
        try {
            log.info("Inicia proceso para buscar los empleados con registro de asistencia");
            listEmpleado = incidenciaEmpleadoSrv.buscarPorParametros(fechaInicio, fechaFin, idEmpresa, idPlanta);
            log.info("Termina proceso para buscar los empleados con registro de asistencia");
        } catch (ToolException ex) {
            log.info("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNAUTHORIZED, "Herramienta auxiliar", ex);
        } catch (ValidationException ex) {
            log.info("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNPROCESSABLE_ENTITY, "Validación", ex);
        } catch (SystemException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.NOT_FOUND, "Servicio externo", ex);
        } catch (RuleException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.CONFLICT, "Regla de negocio", ex);
        } catch (Exception ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorDesdeApiToMovil(ex);
        }
        
        return ResponseEntity.ok(listEmpleado);
    }
    
    @GetMapping("incidencias/empleado/obtenerDetalle")
    public ResponseEntity<?> obtenerDetalle(@RequestParam(required = true) String fechaInicio, @RequestParam(required = true) String fechaFin, 
        @RequestParam(required = false) Integer idEmpleado) {
        EmpleadoAsisDTO registrosEmpleado = null;
        
        try {
            log.info("Inicia proceso para buscar los registro del empleado");
            registrosEmpleado = incidenciaEmpleadoSrv.obtenerRegistrosPorEmpleado(fechaInicio, fechaFin, idEmpleado);
            log.info("Termina proceso para buscar los registro del empleado");
        } catch (ToolException ex) {
            log.info("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNAUTHORIZED, "Herramienta auxiliar", ex);
        } catch (ValidationException ex) {
            log.info("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNPROCESSABLE_ENTITY, "Validación", ex);
        } catch (SystemException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.NOT_FOUND, "Servicio externo", ex);
        } catch (RuleException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.CONFLICT, "Regla de negocio", ex);
        } catch (Exception ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorDesdeApiToMovil(ex);
        }
        
        return ResponseEntity.ok(registrosEmpleado);
    }
    
}
