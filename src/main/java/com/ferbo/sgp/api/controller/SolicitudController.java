package com.ferbo.sgp.api.controller;

import javax.management.RuntimeErrorException;

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
import com.ferbo.sgp.api.model.Incidencia;
import com.ferbo.sgp.api.repository.IncidenciaRepo;
import com.ferbo.sgp.api.service.SolicitudSrv;

@RestController
@RequestMapping("movil")
public class SolicitudController {

    private static Logger log = LogManager.getLogger(SolicitudController.class);

    @Autowired
    private SolicitudSrv solicitudSrv;

    @Autowired 
    private IncidenciaRepo incidenciaRepo;

    @GetMapping(value = "/solicitudes/{id}/estatus", produces = "application/json")
    public ResponseEntity<?> obtenerSolicitudArticuloPorId(@PathVariable Integer id) {

        SolicitudArticuloDTO solicitudA = null;
        SolicitudPrendaDTO solicitudP = null;
        Incidencia incidencia = null;

        try {

            incidencia = incidenciaRepo.findById(id).orElseThrow(() -> new RuntimeException("No existe incidencia con con el id: " + id));

            switch(incidencia.getTipo().getClave()){
                case "A":
                    solicitudA = solicitudSrv.obtenerSolicitudArticulo(incidencia.getSolicitudArticulo().getId());
                    return ResponseEntity.ok(solicitudA);

                case "PR":
                    solicitudP = solicitudSrv.obtenerSolicitudPrenda(incidencia.getSolicitudPrenda().getId());
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
