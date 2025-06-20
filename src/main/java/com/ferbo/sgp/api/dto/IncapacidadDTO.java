package com.ferbo.sgp.api.dto;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 *
 * @author alberto
 */
public class IncapacidadDTO 
{
    private Integer idIncapacidad;
    private String nombreInc;
    private String primerApInc;
    private String segundoApInc;
    private String descipcion;
    private String folio;
    private String clave;
    private OffsetDateTime fechaCaptura;

    public IncapacidadDTO() {
    }

    public Integer getIdIncapacidad() {
        return idIncapacidad;
    }

    public void setIdIncapacidad(Integer idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public String getNombreInc() {
        return nombreInc;
    }

    public void setNombreInc(String nombreInc) {
        this.nombreInc = nombreInc;
    }

    public String getPrimerApInc() {
        return primerApInc;
    }

    public void setPrimerApInc(String primerApInc) {
        this.primerApInc = primerApInc;
    }

    public String getSegundoApInc() {
        return segundoApInc;
    }

    public void setSegundoApInc(String segundoApInc) {
        this.segundoApInc = segundoApInc;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public OffsetDateTime getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(OffsetDateTime fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.idIncapacidad);
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
        final IncapacidadDTO other = (IncapacidadDTO) obj;
        return Objects.equals(this.idIncapacidad, other.idIncapacidad);
    }

    @Override
    public String toString() {
        return "IncapacidadDTO[" + "idIncapacidad=" + idIncapacidad + ", nombreInc=" + nombreInc + ", primerApInc=" + primerApInc 
                + ", segundoApInc=" + segundoApInc + ", descipcionInc=" + descipcion + ", folio=" + folio 
                + ", clave=" + clave + ", fechaCaptura=" + fechaCaptura + ']';
    }
    
}
