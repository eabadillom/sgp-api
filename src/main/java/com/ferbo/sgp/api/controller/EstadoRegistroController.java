package com.ferbo.sgp.api.controller;

import java.util.List;
import com.ferbo.sgp.api.dto.EstatusAsistenciaDTO;
import com.ferbo.sgp.api.service.EstadoRegistroSrv;
import com.ferbo.sgp.api.tool.ErrorResponseBuilder;
import com.ferbo.tools.exception.RuleException;
import com.ferbo.tools.exception.SystemException;
import com.ferbo.tools.exception.ToolException;
import com.ferbo.tools.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("movil")
public class EstadoRegistroController 
{
    private static Logger log = LogManager.getLogger(EstadoRegistroController.class);
    
    @Autowired
    EstadoRegistroSrv estadoRegistroSrv;
    
    @GetMapping(value = "/asistencia/estatus", produces = "application/json")
    public ResponseEntity<?> obtenerRegistrosPorEstado() 
    {
        List<EstatusAsistenciaDTO> listEstatusRegistros = null;
        
        try {
            log.info("Inicia proceso para buscar los estatus de asistencias");
            listEstatusRegistros = estadoRegistroSrv.obtenerEstatusAsistencia();
            log.info("Termina proceso para buscar los estatus de asistencias");
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
        
        return ResponseEntity.ok(listEstatusRegistros);
    }

}
