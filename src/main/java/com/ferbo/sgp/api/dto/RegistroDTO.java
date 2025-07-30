package com.ferbo.sgp.api.dto;

import java.time.OffsetDateTime;

public class RegistroDTO {
    private Integer id;
    private String codigoRegistro; 
    private String nombreEmpleado;
    private String primerApEmpleado;
    private String segundoApEmpleado;
    private OffsetDateTime fechaEntrada;
    
    public RegistroDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
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

    public OffsetDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(OffsetDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        RegistroDTO other = (RegistroDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RegistroParcialDTO [id=" + id + ", codigoRegistro=" + codigoRegistro + ", nombreEmpleado="
                + nombreEmpleado + ", primerApEmpleado=" + primerApEmpleado + ", segundoApEmpleado=" + segundoApEmpleado
                + ", fechaEntrada=" + fechaEntrada + "]";
    }
        
}
