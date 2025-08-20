package com.ferbo.sgp.api.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "det_dia_permiso")
public class DiaPermiso implements Serializable {
    
    private static final long serialVersionUID = 9073893533076521696L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dia_permiso")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "fh_permiso")
    private OffsetDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    @Basic(optional = false)
    private SolicitudPermiso solicitud;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public SolicitudPermiso getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudPermiso solicitud) {
        this.solicitud = solicitud;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final DiaPermiso other = (DiaPermiso) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "DiaPermiso{" + "id=" + id + ", fecha=" + fecha + '}';
    }
    
}
