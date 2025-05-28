package com.ferbo.sgp.api.dto;

import java.time.LocalDateTime;

public class SolicitudBaseDTO {

    private Integer idSolicitud;

    private String estatusSolicitud;

    private LocalDateTime fechaCaptura;

    private LocalDateTime fechaModificacion;

    private String nombreRevisor;
    private String primerApRevisor;
    private String segundoApRevisor;

    private String nombreSolicitante;
    private String primerApSolicitante;
    private String segundoApSolicitante;

    private String descripcionRechazo;

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getEstatusSolicitud() {
        return estatusSolicitud;
    }

    public void setEstatusSolicitud(String estatusSolicitud) {
        this.estatusSolicitud = estatusSolicitud;
    }

    public LocalDateTime getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(LocalDateTime fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getNombreRevisor() {
        return nombreRevisor;
    }

    public void setNombreRevisor(String nombreRevisor) {
        this.nombreRevisor = nombreRevisor;
    }

    public String getPrimerApRevisor() {
        return primerApRevisor;
    }

    public void setPrimerApRevisor(String primerApRevisor) {
        this.primerApRevisor = primerApRevisor;
    }

    public String getSegundoApRevisor() {
        return segundoApRevisor;
    }

    public void setSegundoApRevisor(String segundoApRevisor) {
        this.segundoApRevisor = segundoApRevisor;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getPrimerApSolicitante() {
        return primerApSolicitante;
    }

    public void setPrimerApSolicitante(String primerApSolicitante) {
        this.primerApSolicitante = primerApSolicitante;
    }

    public String getSegundoApSolicitante() {
        return segundoApSolicitante;
    }

    public void setSegundoApSolicitante(String segundoApSolicitante) {
        this.segundoApSolicitante = segundoApSolicitante;
    }

    public String getDescripcionRechazo() {
        return descripcionRechazo;
    }

    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }

    
}
