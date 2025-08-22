package com.ferbo.sgp.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.dto.IncidenciaDTO;
import com.ferbo.sgp.api.dto.IncidenciaPermisoDTO;
import com.ferbo.sgp.api.service.IncidenciaSrv;
import static com.ferbo.sgp.api.tool.ErrorResponseBuilder.construirErrorMovil;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("movil")
public class IncidenciaController {

    private static Logger log = LogManager.getLogger(IncidenciaController.class);

    private static final String TIPO_ERROR_INCIDENCIA = "Incidencia";

    @Autowired
    private IncidenciaSrv incidenciaSrv;

    @GetMapping(value = "/incidencias/{tipo}/{fechaIni}/{fechaFin}", produces = "application/json")
    public ResponseEntity<?> obtenerIncicidenciasPorTipoYPeriodo(@PathVariable String tipo, @PathVariable String fechaIni, @PathVariable String fechaFin) {

        List<IncidenciaDTO> incidenciasDTO = null;
        try {
            log.info("Inicio proceso para obtener todas los incidencias en base a los parametros dados.");
            incidenciasDTO = incidenciaSrv.obtenerIncidenciaTipoEnPeriodo(tipo, fechaIni, fechaFin);
            log.info("Finaliza proceso para obtener todas los incidencias en base a los parametros dados.");
        } catch (RuntimeException ex) {
            log.warn("Problema al obtenr las incidencias en base a los parametros dados. {}", ex);
            return construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_INCIDENCIA, ex);
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener las incidencias. {}", ex);
            return construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_INCIDENCIA, ex);
        }
        return ResponseEntity.ok(incidenciasDTO);
    }

    @PatchMapping("/incidencias/{id}/estatus")
    public ResponseEntity<?> actualizarEstatusIncidencia(@PathVariable Integer id, @RequestBody IncidenciaPermisoDTO body) {
        IncidenciaPermisoDTO incidenciaPermisoDTO = null;
        try {
            log.info("Inicia el proceso para actualizar el estado de la incidencia");
            if(body.getCodigoEstado().equals("C")){
                incidenciaPermisoDTO = incidenciaSrv.eliminarRegistroVacaciones(id, body);
            } else {
                incidenciaPermisoDTO = incidenciaSrv.actualizarEstatusIncidencia(id, body);
            }
            log.info("Finaliza el proceso para actualizar el estado de la incidencia");
        } catch(IllegalArgumentException ex){
            log.error("Hubo algun problema. {}", ex);
            return construirErrorMovil(HttpStatus.BAD_REQUEST, TIPO_ERROR_INCIDENCIA, ex);
        } catch (RuntimeException ex) {
            log.warn("Hubo algun problema en la base de datos. {}", id, ex);
            return construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_INCIDENCIA, ex);
        } catch (Exception ex) {
            log.error("Hubo algun problema. {}", ex);
            return construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_INCIDENCIA, ex);
        } 
        return ResponseEntity.ok(incidenciaPermisoDTO);
    }
    
    @GetMapping(value = "/incidencia/{id}", produces = "application/json")
    public ResponseEntity<?> obtenerIncicidenciasPorID(@PathVariable Integer id) 
    {
        IncidenciaPermisoDTO incidenciasPermisoDTO = null;
        try{
            log.info("Inicio proceso para obtener todas los incidencias en base a los parametros dados.");
            incidenciasPermisoDTO = incidenciaSrv.obtenerIncidenciaPorID(id);
            log.info("Finaliza proceso para obtener todas los incidencias en base a los parametros dados.");
        } catch (RuntimeException ex){
            log.warn("Problema al obtenr las incidencias en base a los parametros dados. {}", ex);
            return construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_INCIDENCIA, ex);
        } catch(Exception ex) {
            log.error("Problema desconocido al obtener las incidencias. {}", ex);
            return construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_INCIDENCIA, ex);
        }
        return ResponseEntity.ok(incidenciasPermisoDTO);
    }
    
}
