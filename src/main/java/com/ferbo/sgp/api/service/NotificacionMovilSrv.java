package com.ferbo.sgp.api.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.t;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.NotificacionMovilDTO;
import com.ferbo.sgp.api.dto.TokenNotificacionDTO;
import com.ferbo.sgp.api.model.ControlMovil;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.model.TokenNotificacion;
import com.ferbo.sgp.api.repository.ControlMovilRepo;
import com.ferbo.sgp.api.repository.TokenNotificacionRepo;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class NotificacionMovilSrv {
    private static final Logger log = LogManager.getLogger(NotificacionMovilSrv.class);

    @Autowired 
    private ControlMovilRepo controlMovilRepo;

    @Autowired
    private TokenNotificacionRepo tokenNotificacionRepo;

    public String enviarNotificacion(NotificacionMovilDTO body) throws FirebaseMessagingException {
        log.info("Inicia proceso para enviar notificacion");

        Notification notification = Notification.builder().setTitle(body.getTitle()).setBody(body.getBody()).build();

        Message message = Message.builder().setToken(body.getToken()).setNotification(notification).build();
    
        log.info("Finaliza proceso para enviar notificacion"); 
        return FirebaseMessaging.getInstance().send(message);
    }

    public void notifocarAdministradores(NotificacionMovilDTO body) throws FirebaseMessagingException{

        if (body.getTitle().isEmpty() || body.getBody().isEmpty()){
            throw new IllegalArgumentException("Los campos para la notificación no pueden ser vacíos");
        }

        List<TokenNotificacion> admistradores = tokenNotificacionRepo.findAllByActivo();

        if(admistradores.isEmpty()){
            throw new RuntimeException("No hay sistemas para notificar");
        }

        for (TokenNotificacion administrador : admistradores) {
            body.setToken(administrador.getToken());
            enviarNotificacion(body);
            log.info("Notificación enviada");
        }

    }

    public void guardarToken(String authHeader, TokenNotificacionDTO nuevoToken){

        String token = authHeader.replace("Bearer ", "");
        
		ControlMovil controlMovil = controlMovilRepo.findByToken(token).orElseThrow(()-> new RuntimeException("No se tiene un sistema asginado"));

        Sistema sistema = controlMovil.getSistema();

        TokenNotificacion tokenNotificacion = tokenNotificacionRepo.findBySistemaYActivo(sistema);

        if(tokenNotificacion != null){
            if(nuevoToken.getToken().equals(tokenNotificacion.getToken())){
                throw new RuntimeException("Ya esta asignado al sistema solicitante del servicio");
            } else {
                tokenNotificacion.setEsValido(Boolean.FALSE);
                tokenNotificacionRepo.save(tokenNotificacion);
            }
        } 

        TokenNotificacion nuevTokenNotificacion = new TokenNotificacion();

        nuevTokenNotificacion.setEsValido(Boolean.TRUE);
        nuevTokenNotificacion.setSistema(sistema);
        nuevTokenNotificacion.setToken(nuevoToken.getToken());

        tokenNotificacionRepo.save(nuevTokenNotificacion);
    }
}
