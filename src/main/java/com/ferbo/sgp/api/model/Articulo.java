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

    @Column(name = "descripcion")
    @Size(max = 45)
	@Basic(optional = false)
    private String descripcion;

    @Column(name = "cantidadMax")
    private Integer cantidadMax;

    @Column(name = "unidad")
    @Size(max = 45)
    private String unidad;

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

	public Integer getCantidadMax() {
		return cantidadMax;
	}

	public void setCantidadMax(Integer cantidadMax) {
		this.cantidadMax = cantidadMax;
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

}
