package com.ferbo.sgp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.dto.UsuarioMovilDTO;
import com.ferbo.sgp.api.service.ControlMovilSrv;
import com.ferbo.sgp.api.service.SistemaSrv;


import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("movil")
public class MovilController {

    private static Logger log = LogManager.getLogger(MovilController.class);

    @Autowired
    SistemaSrv sistemaService;

    @Autowired
    private ControlMovilSrv controlMovilSrv;

    @GetMapping("/generar")
    public ResponseEntity<?> inicioPantalla(HttpServletRequest request, @RequestBody UsuarioMovilDTO body) {
        UsuarioMovilDTO usuario = null; 
        try{
            log.info("Inicia el proceso para gererar el usuario");
            usuario = controlMovilSrv.obtenerUsuario(request, body);
            log.info("Finaliza el proceso para gererar el usuario");
        } catch(RuntimeException rtEx){
            log.warn("Hubo un problema al obtener los datos. {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hubo algun problema al obtener los datos");
        } catch(Exception ex){
            log.error("Problema desconocido. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/verificar")
    public ResponseEntity<?> verificarToken() {
        return ResponseEntity.ok("Acceso autorizado");
    }
}
