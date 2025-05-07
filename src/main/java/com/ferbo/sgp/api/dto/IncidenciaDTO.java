package com.ferbo.sgp.api.dto;

import java.time.OffsetDateTime;

public class IncidenciaDTO {

    private Integer idIncidencia;

    private String codigoEstadoincidencia;

	private String codigoTipoIncidencia;

    private String nombreSolicitante;

    private String primerApSolicitante;

    private String segundoApSolicitante;

    private OffsetDateTime fechaCaptura;

    private Integer solicitudPermiso;

    private Integer solicitudPrenda;
    
    private Integer solicitudArticulo;

	public Integer getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(Integer idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public String getCodigoEstadoincidencia() {
		return codigoEstadoincidencia;
	}

	public void setCodigoEstadoincidencia(String codigoEstadoincidencia) {
		this.codigoEstadoincidencia = codigoEstadoincidencia;
	}

	public String getCodigoTipoIncidencia() {
		return codigoTipoIncidencia;
	}

	public void setCodigoTipoIncidencia(String codigoTipoIncidencia) {
		this.codigoTipoIncidencia = codigoTipoIncidencia;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	public String getPrimerApSolicitante() {
		return primerApSolicitante;
	}

	public void setPrimerApSolicitante(String primerApSolicitante) {
		this.primerApSolicitante = primerApSolicitante;
	}

	public String getSegundoApSolicitante() {
		return segundoApSolicitante;
	}

	public void setSegundoApSolicitante(String segundoApSolicitante) {
		this.segundoApSolicitante = segundoApSolicitante;
	}

	public OffsetDateTime getFechaCaptura() {
		return fechaCaptura;
	}

	public void setFechaCaptura(OffsetDateTime fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}

	public Integer getSolicitudPermiso() {
		return solicitudPermiso;
	}

	public void setSolicitudPermiso(Integer solicitudPermiso) {
		this.solicitudPermiso = solicitudPermiso;
	}

	public Integer getSolicitudPrenda() {
		return solicitudPrenda;
	}

	public void setSolicitudPrenda(Integer idSolicitudPrenda) {
		this.solicitudPrenda = idSolicitudPrenda;
	}

	public Integer getSolicitudArticulo() {
		return solicitudArticulo;
	}

	public void setSolicitudArticulo(Integer idSolicitudArticulo) {
		this.solicitudArticulo = idSolicitudArticulo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idIncidencia == null) ? 0 : idIncidencia.hashCode());
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
		IncidenciaDTO other = (IncidenciaDTO) obj;
		if (idIncidencia == null) {
			if (other.idIncidencia != null)
				return false;
		} else if (!idIncidencia.equals(other.idIncidencia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IncidenciaDTO [idIncidencia=" + idIncidencia + ", codigoEstadoincidencia=" + codigoEstadoincidencia
				+ ", nombreSolicitante=" + nombreSolicitante + ", primerApSolicitante=" + primerApSolicitante
				+ ", segundoApSolicitante=" + segundoApSolicitante + ", fechaCaptura=" + fechaCaptura
				+ ", idSolicitudPermiso=" + solicitudPermiso + ", idSolicitudPrenda=" + solicitudPrenda
				+ ", idSolicitudArticulo=" + solicitudArticulo + "]";
	}

}
