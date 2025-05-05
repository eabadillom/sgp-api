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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.dto.RegistroCompletoDTO;
import com.ferbo.sgp.api.dto.RegistroParcialDTO;
import com.ferbo.sgp.api.service.RegistroAsistenciaSrv;

@RestController
@RequestMapping("movil")
public class RegistroAsistenciaController {

    private static Logger log = LogManager.getLogger(RegistroAsistenciaController.class);

    @Autowired
    private RegistroAsistenciaSrv registroAsistenciaSrv;

    @GetMapping(value = "/registros/{fechaIni}/{fechaFin}/{estatus}", produces = "application/json")
    public ResponseEntity<?> obtenerRegistrosPorEstado(
            @PathVariable String fechaIni,
            @PathVariable String fechaFin,
            @PathVariable String estatus) {
        List<RegistroParcialDTO> registros = null;
        try {
            registros = registroAsistenciaSrv.obtenerPorPeriodoYEstatus(fechaIni, fechaFin, estatus);
        } catch (RuntimeException rtEx) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/registros/{id}/estatus")
    public ResponseEntity<?> obtenerRegistroAsistencia(
            @PathVariable Integer id) {
                RegistroCompletoDTO registro = null;
        try {
            registro = registroAsistenciaSrv.obtenerRegistoPorId(id);
        } catch (RuntimeException rtEx) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(registro);
    }

    
    @PatchMapping("/registros/{id}/estatus")
     public ResponseEntity<?> actualizarRegistroAsistencia(
             @PathVariable Integer id, @RequestBody RegistroCompletoDTO body) {
                 RegistroCompletoDTO registro = null;
         try {
             registro = registroAsistenciaSrv.actualizarEstatusRegistro(id, body);
         } catch (RuntimeException rtEx) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
         } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
         }
         return ResponseEntity.ok(registro);
     }
    

}
