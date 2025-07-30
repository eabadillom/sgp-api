package com.ferbo.sgp.api.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class SolicitudBaseDTO {

    private Long id;

    private String estatusSolicitud;

    private OffsetDateTime fechaCaptura;

    private OffsetDateTime fechaModificacion;

    private String numeroRevisor;

    private String nombreSolicitante;
    private String primerApSolicitante;
    private String segundoApSolicitante;

    private String descripcionRechazo;

    public String getEstatusSolicitud() {
        return estatusSolicitud;
    }

    public void setEstatusSolicitud(String estatusSolicitud) {
        this.estatusSolicitud = estatusSolicitud;
    }

    public OffsetDateTime getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(OffsetDateTime fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public OffsetDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(OffsetDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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

    public String getNumeroRevisor() {
        return numeroRevisor;
    }

    public void setNumeroRevisor(String numeroRevisor) {
        this.numeroRevisor = numeroRevisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
