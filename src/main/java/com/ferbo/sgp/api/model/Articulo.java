package com.ferbo.sgp.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cat_articulo")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo")
    private Integer id;

    @Column(name = "decripcion")
    @Size(max = 45)
	@Basic(optional = false)
    private String descripcion;

    @Column(name = "cantidadMax")
    private Integer canditdadMax;

    @Column(name = "unidad")
    @Size(max = 45)
    private String unidad;

    @Column(name = "detalle")
    @Size(max = 150)
    private String detalle;

    @Column(name = "activo")
    private Boolean activo;

    public Articulo() {
    }

    public Articulo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCanditdadMax() {
        return canditdadMax;
    }

    public void setCanditdadMax(Integer canditdadMax) {
        this.canditdadMax = canditdadMax;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Articulo other = (Articulo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Articulo [id=" + id + ", descripcion=" + descripcion + ", canditdadMax=" + canditdadMax + ", unidad="
                + unidad + ", detalle=" + detalle + ", activo=" + activo + "]";
    }

    
}
