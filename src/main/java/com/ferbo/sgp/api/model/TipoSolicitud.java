package com.ferbo.sgp.api.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alberto
 */
@Entity
@Table(name = "cat_tipo_solicitud")
public class TipoSolicitud 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_solicitud")
    private Integer idTipoSolicitud;
    
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cd_clave")
    private String clave;
    
    @Column(name = "activo")
    private Integer activo;

    public TipoSolicitud() {
    }

    public TipoSolicitud(Integer idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public TipoSolicitud(Integer idTipoSolicitud, String descripcion) {
        this.idTipoSolicitud = idTipoSolicitud;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(Integer idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idTipoSolicitud);
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
        final TipoSolicitud other = (TipoSolicitud) obj;
        return Objects.equals(this.idTipoSolicitud, other.idTipoSolicitud);
    }

    @Override
    public String toString() {
        return "TipoSolicitud[" + "idTipoSolicitud=" + idTipoSolicitud + ", descripcion=" + descripcion + ", clave=" + clave + ", activo=" + activo + ']';
    }
    
}
