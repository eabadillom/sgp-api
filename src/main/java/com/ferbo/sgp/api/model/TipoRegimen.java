package com.ferbo.sgp.api.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cat_tipo_regimen")
public class TipoRegimen {
	
	@Id
	@Column(name = "cd_tp_regimen")
	@Size(max = 5)
	@Basic(optional = false)
	private String clave;
	
	@Column(name = "nb_tp_regimen")
	@Size(max = 150)
	@Basic(optional = false)
	private String nombre;
	
	@Column(name = "fh_vigencia_ini")
	@Basic(optional = false)
	private LocalDate vigenciaInicio;
	
	@Column(name = "fh_vigencia_fin")
	@Basic(optional = true)
	private LocalDate vigenciaFin;
	
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
		TipoRegimen other = (TipoRegimen) obj;
		return Objects.equals(clave, other.clave);
	}

	@Override
	public String toString() {
		return "{\"clave\":\"" + clave + "\", \"nombre\":\"" + nombre + "\", \"vigenciaInicio\":\"" + vigenciaInicio
				+ "\", \"vigenciaFin\":\"" + vigenciaFin + "\"}";
	}

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

	public LocalDate getVigenciaInicio() {
		return vigenciaInicio;
	}

	public void setVigenciaInicio(LocalDate vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}

	public LocalDate getVigenciaFin() {
		return vigenciaFin;
	}

	public void setVigenciaFin(LocalDate vigenciaFin) {
		this.vigenciaFin = vigenciaFin;
	}
}
