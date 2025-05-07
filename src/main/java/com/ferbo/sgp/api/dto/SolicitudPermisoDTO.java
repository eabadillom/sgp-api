package com.ferbo.sgp.api.dto;

import java.time.OffsetDateTime;

/**
 *
 * @author alberto
 */
public class SolicitudPermisoDTO 
{
    private Integer idSolicitud;
    private String nombreEmpleado;
    private String primerApEmpleado;
    private String segundoApEmpleado;
    private OffsetDateTime fechaInicio;
    private OffsetDateTime fechaFin;
    private String claveSolicitud;

    public SolicitudPermisoDTO() {
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getPrimerApEmpleado() {
        return primerApEmpleado;
    }

    public void setPrimerApEmpleado(String primerApEmpleado) {
        this.primerApEmpleado = primerApEmpleado;
    }

    public String getSegundoApEmpleado() {
        return segundoApEmpleado;
    }

    public void setSegundoApEmpleado(String segundoApEmpleado) {
        this.segundoApEmpleado = segundoApEmpleado;
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

    public String getClaveSolicitud() {
        return claveSolicitud;
    }

    public void setClaveSolicitud(String claveSolicitud) {
        this.claveSolicitud = claveSolicitud;
    }

    @Override
    public String toString() {
        return "SolicitudPermisoDTO[" + "idSolicitud=" + idSolicitud + ", nombreEmpleado=" + nombreEmpleado + ", primerApEmpleado=" 
                + primerApEmpleado + ", segundoApEmpleado=" + segundoApEmpleado + ", fechaInicio=" + fechaInicio + ", fechaFin=" 
                + fechaFin + ", claveSolicitud=" + claveSolicitud + ']';
    }
    
}
