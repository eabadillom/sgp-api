package com.ferbo.sgp.api.controller;

import static com.ferbo.sgp.api.tool.ErrorResponseBuilder.construirErrorMovil;

import java.util.List;

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

import com.ferbo.sgp.api.dto.RegistroDetalleDTO;
import com.ferbo.sgp.api.dto.RegistroDTO;
import com.ferbo.sgp.api.service.RegistroAsistenciaSrv;

@RestController
@RequestMapping("movil")
public class RegistroAsistenciaController {

    private static Logger log = LogManager.getLogger(RegistroAsistenciaController.class);

    private static final String TIPO_ERROR_ASISTENCIA = "Asistencia";

    @Autowired
    private RegistroAsistenciaSrv registroAsistenciaSrv;

    @GetMapping(value = "/registros/{fechaIni}/{fechaFin}/{estatus}", produces = "application/json")
    public ResponseEntity<?> obtenerRegistrosPorEstado(
            @PathVariable String fechaIni,
            @PathVariable String fechaFin,
            @PathVariable String estatus) {
        List<RegistroDTO> registros = null;
        try {
            log.info("Inicia proceso de obtencion de registros con estatus {} del periodo desde {} al {}", estatus, fechaIni, fechaFin);
            registros = registroAsistenciaSrv.obtenerPorPeriodoYEstatus(fechaIni, fechaFin, estatus);
            log.info("Finaliza proceso de obtencion de registros con estatus {} del periodo dedde {} al {}", estatus, fechaIni, fechaFin);
        } catch (RuntimeException ex) {
            log.warn("Problema al obtener los registros. Error: {}", ex);
            return construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_ASISTENCIA, ex);
        } catch (Exception ex) {
            log.error("Problema al obtener los registros. Error: {}", ex);
            return construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_ASISTENCIA, ex);
        }
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/registros/{id}/estatus")
    public ResponseEntity<?> obtenerRegistroAsistencia(
            @PathVariable Integer id) {
                RegistroDetalleDTO registro = null;
        try {
            log.info("Inicia proceso de obtencion del registro con id {}", id);
            registro = registroAsistenciaSrv.obtenerRegistoPorId(id);
            log.info("Finaliza proceso de obtencion del registro con id {}", id);
        } catch (RuntimeException ex) {
            log.warn("Problema al obtener el registro. Error: {}", ex);
            return construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_ASISTENCIA, ex);
        } catch (Exception ex) {
            log.error("Problema al obtener el registro. Error: {}", ex);
            return construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_ASISTENCIA, ex);
        }
        return ResponseEntity.ok(registro);
    }

    
    @PatchMapping("/registros/{id}/estatus")
     public ResponseEntity<?> actualizarRegistroAsistencia(
             @PathVariable Integer id, @RequestBody RegistroDetalleDTO body) {
                 RegistroDetalleDTO registro = null;
         try {
            log.info("Inicia proceso para actualizar el estado del registro con id {}", id);
             registro = registroAsistenciaSrv.actualizarEstatusRegistro(id, body);
             log.info("Finaliza proceso para actualizar el estado del registro con id {}", id);
         } catch (RuntimeException ex) {
            log.warn("Problema obtener el estado del registro con id {}. Error: {}", id, ex);
             return construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_ASISTENCIA, ex);
         } catch (Exception ex) {
            log.warn("Problema actualizar el estado del registro con id {}. Error: {}", id, ex);
            return construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_ASISTENCIA, ex);
         }
         return ResponseEntity.ok(registro);
     }
    

}
