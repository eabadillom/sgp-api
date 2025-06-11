package com.ferbo.sgp.api.dto;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 *
 * @author alberto
 */
public class IncapacidadGuardarDetalleDTO 
{
    private Integer idIncapacidad;
    private Integer idEmpleadoInc;
    private String idEmpleadoRev;
    private Integer tipoIncapacidad;
    private Integer controlIncapacidad;
    private Integer riesgoTrabajo;
    private Integer tipoRiesgo;
    private String folio;
    private Integer diasAutorizados;
    private OffsetDateTime fechaInicio;
    private String descripcion;
    private String estatusIncapacidad;

    public IncapacidadGuardarDetalleDTO() {
    }

    public IncapacidadGuardarDetalleDTO(Integer idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public Integer getIdIncapacidad() {
        return idIncapacidad;
    }

    public void setIdIncapacidad(Integer idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public Integer getIdEmpleadoInc() {
        return idEmpleadoInc;
    }

    public void setIdEmpleadoInc(Integer idEmpleadoInc) {
        this.idEmpleadoInc = idEmpleadoInc;
    }

    public String getIdEmpleadoRev() {
        return idEmpleadoRev;
    }

    public void setIdEmpleadoRev(String idEmpleadoRev) {
        this.idEmpleadoRev = idEmpleadoRev;
    }

    public Integer getTipoIncapacidad() {
        return tipoIncapacidad;
    }

    public void setTipoIncapacidad(Integer tipoIncapacidad) {
        this.tipoIncapacidad = tipoIncapacidad;
    }

    public Integer getControlIncapacidad() {
        return controlIncapacidad;
    }

    public void setControlIncapacidad(Integer controlIncapacidad) {
        this.controlIncapacidad = controlIncapacidad;
    }

    public Integer getRiesgoTrabajo() {
        return riesgoTrabajo;
    }

    public void setRiesgoTrabajo(Integer riesgoTrabajo) {
        this.riesgoTrabajo = riesgoTrabajo;
    }

    public Integer getTipoRiesgo() {
        return tipoRiesgo;
    }

    public void setTipoRiesgo(Integer tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Integer getDiasAutorizados() {
        return diasAutorizados;
    }

    public void setDiasAutorizados(Integer diasAutorizados) {
        this.diasAutorizados = diasAutorizados;
    }

    public OffsetDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(OffsetDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstatusIncapacidad() {
        return estatusIncapacidad;
    }

    public void setEstatusIncapacidad(String estatusIncapacidad) {
        this.estatusIncapacidad = estatusIncapacidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idIncapacidad);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IncapacidadGuardarDetalleDTO other = (IncapacidadGuardarDetalleDTO) obj;
        return Objects.equals(this.idIncapacidad, other.idIncapacidad);
    }

    @Override
    public String toString() {
        return "IncapacidadGuardarDetalleDTO[" + "idIncapacidad=" + idIncapacidad + ", idEmpleadoInc=" + idEmpleadoInc 
                + ", idEmpleadoRev=" + idEmpleadoRev + ", tipoIncapacidad=" + tipoIncapacidad + ", controlIncapacidad=" 
                + controlIncapacidad + ", riesgoTrabajo=" + riesgoTrabajo + ", tipoRiesgo=" + tipoRiesgo + ", folio=" + folio 
                + ", diasAutorizados=" + diasAutorizados + ", fechaInicio=" + fechaInicio + ", descripcion=" + descripcion 
                + ", estatusIncapacidad=" + estatusIncapacidad + ']';
    }
    
    
}
