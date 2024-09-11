package com.ferbo.sgp.api.model;

import java.time.LocalDate;
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
import javax.validation.constraints.Size;

@Entity
@Table(name = "cat_empresa")
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa")
	private Integer id;
	
	@Column(name = "descripcion")
	@Size(max = 45)
	@Basic(optional = false)
	private String descripcion;
	
	@Column(name = "activo")
	@Basic(optional = false)
	private Integer activo;
	
	@Column(name = "nb_razon_social")
	@Size(max = 150)
	private String razonSocial;
	
	@Column(name = "tp_persona")
	@Size(max = 1)
	private String tipoPersona;
	
	@Column(name = "nb_regimen_capital")
	@Size(max = 150)
	private String regimenCapital;
	
	@Column(name = "nb_rfc")
	@Size(max = 20)
	private String rfc;
	
	@Column(name = "fh_inicio_op")
	private LocalDate inicioOperaciones;
	
	@Column(name = "fh_ult_cambio")
	private LocalDate ultimoCambio;
	
	@Column(name = "st_padron")
	@Size(max = 1)
	private String statusPadron;
	
	@ManyToOne
	@JoinColumn(name = "cd_regimen", referencedColumnName = "cd_regimen")
	private RegimenFiscal regimenFiscal;
	
	@Column(name = "nu_reg_pat")
	@Size(max = 20)
	private String registroPatronal;
	
	@Column(name = "nu_cp")
	@Size(max = 5)
	private String codigoPostal;
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"descripcion\":\"" + descripcion + "\", \"activo\":\"" + activo
				+ "\", \"razonSocial\":\"" + razonSocial + "\", \"tipoPersona\":\"" + tipoPersona
				+ "\", \"regimenCapital\":\"" + regimenCapital + "\", \"rfc\":\"" + rfc + "\", \"inicioOperaciones\":\""
				+ inicioOperaciones + "\", \"ultimoCambio\":\"" + ultimoCambio + "\", \"statusPadron\":\""
				+ statusPadron + "\", \"registroPatronal\":\"" + registroPatronal + "\", \"codigoPostal\":\""
				+ codigoPostal + "\"}";
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

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getRegimenCapital() {
		return regimenCapital;
	}

	public void setRegimenCapital(String regimenCapital) {
		this.regimenCapital = regimenCapital;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public LocalDate getInicioOperaciones() {
		return inicioOperaciones;
	}

	public void setInicioOperaciones(LocalDate inicioOperaciones) {
		this.inicioOperaciones = inicioOperaciones;
	}

	public LocalDate getUltimoCambio() {
		return ultimoCambio;
	}

	public void setUltimoCambio(LocalDate ultimoCambio) {
		this.ultimoCambio = ultimoCambio;
	}

	public String getStatusPadron() {
		return statusPadron;
	}

	public void setStatusPadron(String statusPadron) {
		this.statusPadron = statusPadron;
	}

	public RegimenFiscal getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(RegimenFiscal regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	public String getRegistroPatronal() {
		return registroPatronal;
	}

	public void setRegistroPatronal(String registroPatronal) {
		this.registroPatronal = registroPatronal;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
}
