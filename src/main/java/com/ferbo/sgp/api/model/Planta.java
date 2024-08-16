package com.ferbo.sgp.api.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cat_planta")
public class Planta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_planta")
	private Integer id;
	
	@Column(name = "descripcion")
	@Size(max = 45)
	@Basic(optional = false)
	private String descripcion;
	
	@Column(name = "activo")
	private Integer activo;
	
	@OneToMany(mappedBy = "planta")
	private List<InformacionEmpresa> informacionEmpresas;
	
	@OneToMany(mappedBy = "planta")
	private List<Sistema> sistemas;

	public List<Sistema> getSistemas() {
		return sistemas;
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
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

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public List<InformacionEmpresa> getInformacionEmpresas() {
		return informacionEmpresas;
	}
	
	public void setInformacionEmpresas(List<InformacionEmpresa> informacionEmpresas) {
		this.informacionEmpresas = informacionEmpresas;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(activo, descripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		return Objects.equals(activo, other.activo) && Objects.equals(descripcion, other.descripcion);
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"descripcion\":\"" + descripcion + "\", \"activo\":\"" + activo + "\"}";
	}

}
