package com.ferbo.sgp.api.model.request;

import java.util.List;

public class FpClientRequest {
	
	protected List<BiometricoRequest> biometricos = null;

	public List<BiometricoRequest> getBiometricos() {
		return biometricos;
	}

	public void setBiometricos(List<BiometricoRequest> biometricos) {
		this.biometricos = biometricos;
	}
	
	

}
