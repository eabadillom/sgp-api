package com.ferbo.sgp.api.dto;

import java.time.OffsetDateTime;

/**
 *
 * @author alberto
 */
public class IncidenciaPermisoDTO 
{
    private Integer idIncidencia;
    private String codigoEstado;
    private String nombreSolicitante;
    private String primerApSolicitante;
    private String segundoApSolicitante;
    private OffsetDateTime fechaInicio;
    private OffsetDateTime fechaFin;
    private String empleadoRev;
    private String descripcionRechazo;

    public IncidenciaPermisoDTO() {
    }

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
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

    public OffsetDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(OffsetDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public OffsetDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(OffsetDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEmpleadoRev() {
        return empleadoRev;
    }

    public void setEmpleadoRev(String empleadoRev) {
        this.empleadoRev = empleadoRev;
    }

    public String getDescripcionRechazo() {
        return descripcionRechazo;
    }

    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }
    
}
