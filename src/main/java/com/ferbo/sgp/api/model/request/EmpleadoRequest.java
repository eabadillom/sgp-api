package com.ferbo.sgp.api.model.request;

import java.util.Objects;

public class EmpleadoRequest {
	
	protected String numeroEmpleado;
	
	protected String nombre;
	
	protected String primerApellido;
	
	protected String segundoApellido;
	
	@Override
	public int hashCode() {
		return Objects.hash(numeroEmpleado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoRequest other = (EmpleadoRequest) obj;
		return Objects.equals(numeroEmpleado, other.numeroEmpleado);
	}

	@Override
	public String toString() {
		return "{\"numeroEmpleado\":\"" + numeroEmpleado + "\"}";
	}

	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
}
