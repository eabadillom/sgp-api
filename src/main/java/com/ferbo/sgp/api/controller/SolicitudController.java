package com.ferbo.sgp.api.controller;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.dto.IncidenciaDTO;
import com.ferbo.sgp.api.dto.SolicitudArticuloDTO;
import com.ferbo.sgp.api.dto.SolicitudPrendaDTO;
import com.ferbo.sgp.api.service.SolicitudSrv;

@RestController
@RequestMapping("movil")
public class SolicitudController {

    private static Logger log = LogManager.getLogger(SolicitudController.class);

    @Autowired
    private SolicitudSrv solicitudSrv;

    @GetMapping(value = "/solicitudes/{tipo}/{id}", produces = "application/json")
    public ResponseEntity<?> obtenerSolicitudArticuloPorId(@PathVariable String tipo, @PathVariable Long id) {

        SolicitudArticuloDTO solicitudA = null;
        SolicitudPrendaDTO solicitudP = null;

        tipo = tipo.trim().toLowerCase();

        try {

            switch(tipo){
                case "articulo":
                    solicitudA = solicitudSrv.obtenerSolicitudArticulo(id);
                    return ResponseEntity.ok(solicitudA);

                case "uniforme":
                    solicitudP = solicitudSrv.obtenerSolicitudPrenda(id);
                    return ResponseEntity.ok(solicitudP);

                default:
                    Error rtEx = new Error();
                    throw new RuntimeErrorException(rtEx, "El tipo de solicituda introducida no existe");
            }

        } catch (RuntimeException rtEx) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
    }

    @PatchMapping(value = "/solicitudes/{id}/estatus", produces = "aplicaction/json")
    public ResponseEntity<?> actualizarEstatuaSolicitud(@PathVariable Integer id, @RequestBody IncidenciaDTO body){

        try{
            log.info("Inicia el proceso para actualizar incidencia y solicitud");
            solicitudSrv.actualizarSolicitud(id, body);
            log.info("Finaliza el preoceso para actualizar incidencia y solicitud");
        } catch (RuntimeException rtEx){
            log.warn("Hubo algun problema al actualizar: {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Hubo algun problema al actualizar: {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte al administrador del sistema");
        }
        return ResponseEntity.ok("La solicitud se ha actualizado correctamente");
    } 
}
