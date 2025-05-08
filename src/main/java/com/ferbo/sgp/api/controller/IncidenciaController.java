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
import com.ferbo.sgp.api.dto.SolicitudPermisoDTO;
import com.ferbo.sgp.api.service.IncidenciaSrv;
import com.ferbo.sgp.api.service.SolicitudPermisoSrv;

@RestController
@RequestMapping("movil")
public class IncidenciaController {

    private static Logger log = LogManager.getLogger(IncidenciaController.class);

    @Autowired
    private IncidenciaSrv incidenciaSrv;
    
    @Autowired
    private SolicitudPermisoSrv solicitudPermisoSrv;

    @GetMapping(value = "/incidencias/{tipo}/{estatus}/{fechaIni}/{fechaFin}", produces = "application/json")
    public ResponseEntity<?> obtenerIncicidenciasPorTipoEstusYPeriodo(@PathVariable String tipo,
            @PathVariable String estatus, @PathVariable String fechaIni, @PathVariable String fechaFin) {

        List<IncidenciaDTO> incidenciasDTO = null;
        try {
            log.info("Inicio proceso para obtener todas los incidencias en base a los parametros dados.");
            incidenciasDTO = incidenciaSrv.obtenerIncidenciaTipoEstatusEnPeriodo(tipo, estatus, fechaIni, fechaFin);
            log.info("Finaliza proceso para obtener todas los incidencias en base a los parametros dados.");
        } catch (RuntimeException rtEx) {
            log.warn("Problema al obtenr las incidencias en base a los parametros dados. {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener las incidencias. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(incidenciasDTO);
    }

    @PatchMapping("/incidencias/{id}/estatus")
    public ResponseEntity<?> actualizarEstatusIncidencia(@PathVariable Integer id, IncidenciaDTO body) {
        IncidenciaDTO incidenciaDTO = null;
        try {
            log.info("Inicia el proceso para actualizar el estado de la incidencia");
            incidenciaDTO = incidenciaSrv.actualizarEstatusIncidencia(id, body);
            log.info("Finaliza el proceso para actualizar el estado de la incidencia");
        } catch (RuntimeException rtEx) {
            log.warn("Hubo algun problema en la base de datos. {}", id, rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Hubo algun problema. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.ok(incidenciaDTO);
    }
    
    @GetMapping(value = "/incidencia/{id}", produces = "application/json")
    public ResponseEntity<?> obtenerIncicidenciasPorID(@PathVariable Integer id) 
    {
        IncidenciaDTO incidenciasDTO = null;
        try{
            log.info("Inicio proceso para obtener todas los incidencias en base a los parametros dados.");
            incidenciasDTO = incidenciaSrv.obtenerIncidenciaPorID(id);
            log.info("Finaliza proceso para obtener todas los incidencias en base a los parametros dados.");
        } catch (RuntimeException rtEx){
            log.warn("Problema al obtenr las incidencias en base a los parametros dados. {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch(Exception ex) {
            log.error("Problema desconocido al obtener las incidencias. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(incidenciasDTO);
    }
    
    @GetMapping(value = "/solicitud/permiso/{id}", produces = "application/json")
    public ResponseEntity<?> obtenerSolicitudPermisoPorID(@PathVariable Integer id)
    {
        SolicitudPermisoDTO solicitudPermiso = null;
        try 
        {
            log.info("Inicia proceso de obtencion de la solicitud de permiso con id {}", id);
            solicitudPermiso = solicitudPermisoSrv.obtenerSolicitudPorId(id);
            log.info("Finaliza proceso de obtencion de la solicitud de permiso con id {}", id);
        }catch (RuntimeException rtEx) {
            log.warn("Problema al obtener el registro. Error: {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Problema al obtener el registro. Error: {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(solicitudPermiso);
    }
    
}
