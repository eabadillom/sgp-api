package com.ferbo.sgp.api.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author alberto
 */
@Entity
@Table(name = "cat_estatus_incapacidad")
public class EstatusIncapacidad 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_estatus_inc")
    private Integer idEstatusSolicitud;
    
    @Column(name = "nb_clave")
    private String clave;
    
    @Column(name = "nb_descripcion")
    private String descripcion;

    public EstatusIncapacidad() {
    }

    public EstatusIncapacidad(Integer idEstatusSolicitud) {
        this.idEstatusSolicitud = idEstatusSolicitud;
    }

    public Integer getIdEstatusSolicitud() {
        return idEstatusSolicitud;
    }

    public void setIdEstatusSolicitud(Integer idEstatusSolicitud) {
        this.idEstatusSolicitud = idEstatusSolicitud;
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
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.idEstatusSolicitud);
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
        final EstatusIncapacidad other = (EstatusIncapacidad) obj;
        return Objects.equals(this.idEstatusSolicitud, other.idEstatusSolicitud);
    }

    @Override
    public String toString() {
        return "EstatusIncapacidad[" + "idEstatusSolicitud=" + idEstatusSolicitud + ", clave=" + clave + ", descripcion=" + descripcion + ']';
    }
    
}
