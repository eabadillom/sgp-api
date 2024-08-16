package com.ferbo.sgp.api.exception;

public class SgpApiException extends Exception {

	private static final long serialVersionUID = -5926570919082698332L;
	
	public SgpApiException() {
		super();
	}
	
	public SgpApiException(String message) {
		super(message);
	}
	
	public SgpApiException(Throwable cause) {
		super(cause);
	}
	
	public SgpApiException(String message, Throwable cause) {
		super(message, cause);
	}
}
