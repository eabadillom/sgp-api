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
@Table(name = "cat_regimen_fiscal")
public class RegimenFiscal {
	
	@Id
	@Column(name = "cd_regimen")
	@Basic(optional = false)
	private String clave;
	
	@Column(name = "nb_regimen")
	@Size(max = 255)
	@Basic(optional = false)
	private String nombre;
	
	@Column(name = "st_per_fisica")
	@Basic(optional = false)
	private Boolean personaFisica;
	
	@Column(name = "st_per_moral")
	@Basic(optional = false)
	private Boolean personaMoral;
	
	@Column(name = "fh_vigencia_ini")
	@Basic(optional = false)
	private LocalDate vigenciaInicio;
	
	@Column(name = "fh_vigencia_fin")
	@Basic(optional = true)
	private LocalDate vigenciaFin;

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

	public Boolean getPersonaFisica() {
		return personaFisica;
	}

	public void setPersonaFisica(Boolean personaFisica) {
		this.personaFisica = personaFisica;
	}

	public Boolean getPersonaMoral() {
		return personaMoral;
	}

	public void setPersonaMoral(Boolean personaMoral) {
		this.personaMoral = personaMoral;
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
		RegimenFiscal other = (RegimenFiscal) obj;
		return Objects.equals(clave, other.clave);
	}

	@Override
	public String toString() {
		return "{\"clave\":\"" + clave + "\", \"nombre\":\"" + nombre + "\", \"personaFisica\":\"" + personaFisica
				+ "\", \"personaMoral\":\"" + personaMoral + "\", \"vigenciaInicio\":\"" + vigenciaInicio
				+ "\", \"vigenciaFin\":\"" + vigenciaFin + "\"}";
	}
}
