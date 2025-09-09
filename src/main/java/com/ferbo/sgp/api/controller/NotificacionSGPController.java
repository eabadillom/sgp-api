
package com.ferbo.sgp.api.controller;

import com.ferbo.sgp.api.dto.NotificacionMovilDTO;
import com.ferbo.sgp.api.service.NotificacionMovilSrv;
import com.google.firebase.messaging.FirebaseMessagingException;

import static com.ferbo.sgp.api.tool.ErrorResponseBuilder.construirErrorMovil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fp-client")
@PreAuthorize("hasRole('FP_CLIENT')")
public class NotificacionSGPController {
    
    private static Logger log = LogManager.getLogger(NotificacionSGPController.class);

    private static final String TIPO_ERROR_NOTIFICACION = "Notificación";
    
    @Autowired
    private NotificacionMovilSrv notificacionMovilSrv;

    @PostMapping("/enviar/notificacion")
    public ResponseEntity<?> enviarNotificacion(@RequestBody NotificacionMovilDTO body){
        try {
            log.info("Inicia proceso para notificar via movil a los administradores");
            notificacionMovilSrv.notifocarAdministradores(body);
            log.info("Finaliza proceso para notificar via movil a los administradores");
            return ResponseEntity.ok("Se ha notificado a los admistradores via movil de forma correcta");
        } catch (FirebaseMessagingException ex){
            log.warn("La notificacion no pudo ser enviada. {}", ex);
            return construirErrorMovil(HttpStatus.BAD_REQUEST, TIPO_ERROR_NOTIFICACION, ex);
        } catch(RuntimeException ex){
            log.warn("La notificacion no pudo ser enviada {}", ex);
            return construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_NOTIFICACION, ex);
        } catch (Exception ex){
            log.error("La notificacion no pudo ser enviada. {}", ex);
            return construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_NOTIFICACION, ex);
        }
    }
}
