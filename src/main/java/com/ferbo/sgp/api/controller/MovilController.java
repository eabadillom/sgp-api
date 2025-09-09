package com.ferbo.sgp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.dto.UsuarioMovilDTO;
import com.ferbo.sgp.api.service.ControlMovilSrv;
import com.ferbo.sgp.api.service.SistemaSrv;

import static com.ferbo.sgp.api.tool.ErrorResponseBuilder.construirErrorMovil;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("movil")
public class MovilController {

    private static Logger log = LogManager.getLogger(MovilController.class);

    private static final String TIPO_ERROR_ACCESO = "Acceso";

    @Autowired
    SistemaSrv sistemaService;

    @Autowired
    private ControlMovilSrv controlMovilSrv;

    @GetMapping("/generar")
    public ResponseEntity<?> inicioPantalla(HttpServletRequest request, @RequestBody UsuarioMovilDTO body) {
        UsuarioMovilDTO usuario = null; 
        try{
            log.info("Inicia el proceso para generar el usuario");
            usuario = controlMovilSrv.obtenerUsuario(request, body);
            log.info("Finaliza el proceso para generar el usuario");
        } catch(RuntimeException ex){
            log.warn("Hubo un problema al obtener los datos. {}", ex);
            return construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_ACCESO, ex);
        } catch(Exception ex){
            log.error("Problema desconocido. {}", ex);
            return construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_ACCESO, ex);
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/verificar")
    public ResponseEntity<?> verificarToken() {
        return ResponseEntity.ok("Acceso autorizado");
    }

    @GetMapping("/deshabilitar")
    public ResponseEntity<?> deshabilitarToken(@RequestHeader("Authorization") String authHeader){
        try {
        log.info("Inicia proceso para desahibilitar el token del sistema.");
        String respuesta = controlMovilSrv.deshabilitarToken(authHeader);
        log.info("Finaliza proceso para desahibilitar el token del sistema.");
        return ResponseEntity.ok(respuesta);
        
       } catch (RuntimeException ex) {
        log.warn("Hubo un problema al desahibilitar el token del sistema. {}", ex);
        return construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_ACCESO, ex);
       } catch (Exception ex) {
        log.error("Hubo un problema al desahibilitar el token del sistema. {}", ex);
        return construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_ACCESO, ex);
       }
    }
}
