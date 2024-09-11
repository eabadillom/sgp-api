package com.ferbo.sgp.api.model.response;

import com.ferbo.sgp.api.model.request.EmpleadoRequest;

public class EmpleadoResponse extends EmpleadoRequest {
	
	private Integer codigoError;
	
	private String mensajeError;

	public Integer getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(Integer codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
}
