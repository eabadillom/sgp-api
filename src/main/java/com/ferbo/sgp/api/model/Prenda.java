package com.ferbo.sgp.api.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cat_prenda")
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenda")
    private Integer id;

    @Column(name = "descripcion")
    @Size(max = 45)
	@Basic(optional = false)
    private String descripcion;

    @Column(name = "precio", precision = 5, scale = 2, nullable = false)
    private BigDecimal precio;

    @Column(name = "cantidadMax")
    private Integer cantidadMax;

    @Column(name = "detalle")
    @Size(max = 150)
    private String detalle;

    @Column(name = "activo")
    private Boolean activo;

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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidadMax() {
        return cantidadMax;
    }

    public void setCantidadMax(Integer cantidadMax) {
        this.cantidadMax = cantidadMax;
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
    
}
