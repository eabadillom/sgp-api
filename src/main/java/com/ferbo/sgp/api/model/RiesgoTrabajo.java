package com.ferbo.sgp.api.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alberto
 */
@Entity
@Table(name = "cat_secuela_riesgo_trabajo")
public class RiesgoTrabajo 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "cd_sec_riesgo_trabajo")
    private Integer idSecRiesgoTrabajo;
    
    @NotNull
    @Column(name = "nb_clave")
    private String clave;
    
    @NotNull
    @Column(name = "nb_descripcion")
    private String descripcion;

    public RiesgoTrabajo() {
    }

    public RiesgoTrabajo(Integer idSecRiesgoTrabajo) {
        this.idSecRiesgoTrabajo = idSecRiesgoTrabajo;
    }

    public Integer getIdSecRiesgoTrabajo() {
        return idSecRiesgoTrabajo;
    }

    public void setIdSecRiesgoTrabajo(Integer idSecRiesgoTrabajo) {
        this.idSecRiesgoTrabajo = idSecRiesgoTrabajo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idSecRiesgoTrabajo);
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
        final RiesgoTrabajo other = (RiesgoTrabajo) obj;
        return Objects.equals(this.idSecRiesgoTrabajo, other.idSecRiesgoTrabajo);
    }

    @Override
    public String toString() {
        return "RiesgoTrabajo[" + "idSecRiesgoTrabajo=" + idSecRiesgoTrabajo + ", clave=" + clave + ", descripcion=" + descripcion + ']';
    }
    
}
