package com.ferbo.sgp.api.dto;

import java.util.Objects;

/**
 *
 * @author alberto
 */
public class EmpleadoIncDTO 
{
    private Integer idEmpleado;
    private String nombre;
    private String primerApEmpleado;
    private String segundoApEmpleado;

    public EmpleadoIncDTO() {
    }

    public EmpleadoIncDTO(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApEmpleado() {
        return primerApEmpleado;
    }

    public void setPrimerApEmpleado(String primerApEmpleado) {
        this.primerApEmpleado = primerApEmpleado;
    }

    public String getSegundoApEmpleado() {
        return segundoApEmpleado;
    }

    public void setSegundoApEmpleado(String segundoApEmpleado) {
        this.segundoApEmpleado = segundoApEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idEmpleado);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmpleadoIncDTO other = (EmpleadoIncDTO) obj;
        return Objects.equals(this.idEmpleado, other.idEmpleado);
    }

    @Override
    public String toString() {
        return "EmpleadoDTO[" + "idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", primerApEmpleado=" + primerApEmpleado 
                + ", segundoApEmpleado=" + segundoApEmpleado + ']';
    }
    
}
