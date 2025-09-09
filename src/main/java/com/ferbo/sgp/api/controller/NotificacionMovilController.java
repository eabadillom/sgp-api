package com.ferbo.sgp.api.controller;

import static com.ferbo.sgp.api.tool.ErrorResponseBuilder.construirErrorMovil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.dto.TokenNotificacionDTO;
import com.ferbo.sgp.api.service.NotificacionMovilSrv;

@RestController
@RequestMapping("movil")
public class NotificacionMovilController {

   private static Logger log = LogManager.getLogger(NotificacionMovilController.class);

   private static final String TIPO_ERROR_NOTIFICACION = "Notificiación";

   @Autowired
   private NotificacionMovilSrv notificacionMovilSrv;

    @PostMapping("/generar/notificacion")
    public ResponseEntity <?> generarToken(@RequestHeader("Authorization") String authHeader, @RequestBody TokenNotificacionDTO nuevoToken) {
       try {
        log.info("Inicia proceso para guardar el token de notificacion");
        notificacionMovilSrv.guardarToken(authHeader, nuevoToken);
        log.info("Finaliza proceso para guardar el token de notificacion");
        return ResponseEntity.ok("El token de notificacion se guardo exitosamente");
        
       } catch (RuntimeException ex) {
        log.warn("Hubo un problema al guardar el token de notificacion. {}", ex);
        return construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_NOTIFICACION, ex);
       } catch (Exception ex) {
        log.error("Hubo un problema al guardar el token de notificacion. {}", ex);
        return construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_NOTIFICACION, ex);
       }
    }
}
