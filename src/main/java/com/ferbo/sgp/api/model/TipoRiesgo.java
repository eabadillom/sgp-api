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
@Table(name = "cat_tipo_riesgo")
public class TipoRiesgo 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "cd_tp_riesgo")
    private Integer idTipoRiesgo;
    
    @NotNull
    @Column(name = "nb_clave")
    private String clave;
    
    @NotNull
    @Column(name = "nb_descripcion")
    private String descripcion;

    public TipoRiesgo() {
    }

    public TipoRiesgo(Integer idTipoRiesgo) {
        this.idTipoRiesgo = idTipoRiesgo;
    }

    public Integer getIdTipoRiesgo() {
        return idTipoRiesgo;
    }

    public void setIdTipoRiesgo(Integer idTipoRiesgo) {
        this.idTipoRiesgo = idTipoRiesgo;
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
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idTipoRiesgo);
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
        final TipoRiesgo other = (TipoRiesgo) obj;
        return Objects.equals(this.idTipoRiesgo, other.idTipoRiesgo);
    }

    @Override
    public String toString() {
        return "TipoRiesgo[" + "idTipoRiesgo=" + idTipoRiesgo + ", clave=" + clave + ", descripcion=" + descripcion + ']';
    }
    
}
