package com.ferbo.sgp.api.model;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cat_tipo_jornada")
public class TipoJornada {
	
	@Id
	@Column(name = "cd_jornada")
	@Size(max = 5)
	@Basic(optional = false)
	private String clave;
	
	@Column(name = "nb_jornada")
	@Size(max = 150)
	@Basic(optional = false)
	private String nombre;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clave);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoJornada other = (TipoJornada) obj;
		return Objects.equals(clave, other.clave);
	}

	@Override
	public String toString() {
		return "{\"clave\":\"" + clave + "\", \"nombre\":\"" + nombre + "\"}";
	}
}
