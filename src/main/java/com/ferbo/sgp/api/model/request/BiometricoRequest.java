package com.ferbo.sgp.api.model.request;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.ferbo.sgp.api.model.response.FotografiaResponse;

public class BiometricoRequest {
	protected String uuid = null;
	protected String numero = null;
	protected String biometrico1 = null;
	protected String biometrico2 = null;
	protected String captura = null;
	protected OffsetDateTime horaEntrada = null;
	protected OffsetDateTime horaSalida = null;
	
	protected FotografiaResponse fotografia = null;
	
	public String getUuid() {
		return this.uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBiometrico1() {
		return biometrico1;
	}
	public void setBiometrico1(String biometrico1) {
		this.biometrico1 = biometrico1;
	}
	public String getBiometrico2() {
		return biometrico2;
	}
	public void setBiometrico2(String biometrico2) {
		this.biometrico2 = biometrico2;
	}
	public String getCaptura() {
		return captura;
	}
	public void setCaptura(String captura) {
		this.captura = captura;
	}
	public OffsetDateTime getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(OffsetDateTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public OffsetDateTime getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(OffsetDateTime horaSalida) {
		this.horaSalida = horaSalida;
	}
	public FotografiaResponse getFotografia() {
		return fotografia;
	}
	public void setFotografia(FotografiaResponse fotografia) {
		this.fotografia = fotografia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(biometrico1, biometrico2, captura, horaEntrada, horaSalida, numero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BiometricoRequest other = (BiometricoRequest) obj;
		return Objects.equals(biometrico1, other.biometrico1) && Objects.equals(biometrico2, other.biometrico2)
				&& Objects.equals(captura, other.captura) && Objects.equals(horaEntrada, other.horaEntrada)
				&& Objects.equals(horaSalida, other.horaSalida) && Objects.equals(numero, other.numero);
	}
	@Override
	public String toString() {
		return "{\"uuid\":\"" + uuid + "\", \"numero\":\"" + numero + "\", \"biometrico1\":\"" + biometrico1
				+ "\", \"biometrico2\":\"" + biometrico2 + "\", \"captura\":\"" + captura + "\", \"horaEntrada\":\""
				+ horaEntrada + "\", \"horaSalida\":\"" + horaSalida + "\", \"fotografia\":\"" + fotografia + "\"}";
	}
	
}
