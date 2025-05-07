package com.ferbo.sgp.api.controller;

import com.ferbo.sgp.api.dto.SolicitudPermisoDTO;
import com.ferbo.sgp.api.service.SolicitudPermisoSrv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alberto
 */
@RestController
@RequestMapping("movil")
public class SolicitudPermisoController 
{
    private static Logger log = LogManager.getLogger(SolicitudPermisoController.class);
    
    @Autowired
    private SolicitudPermisoSrv solicitudPermisoSrv;
    
    @GetMapping("/solicitud/permiso/{id}")
    public ResponseEntity<?> obtenerRegistroAsistencia(@PathVariable Integer id)
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
