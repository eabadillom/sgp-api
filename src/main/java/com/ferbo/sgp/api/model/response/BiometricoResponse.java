package com.ferbo.sgp.api.model.response;

import com.ferbo.sgp.api.model.request.BiometricoRequest;

public class BiometricoResponse extends BiometricoRequest {
	
	protected String token = null;
	protected Integer codigoError = null;
	protected String mensajeError = null;
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
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
