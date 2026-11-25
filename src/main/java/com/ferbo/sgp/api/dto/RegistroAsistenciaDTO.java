package com.ferbo.sgp.api.dto;

import java.time.OffsetDateTime;

public class RegistroAsistenciaDTO 
{
    private Integer id;
    private OffsetDateTime fechaEntrada;
    private String codigoRegistro;

    public RegistroAsistenciaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OffsetDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(OffsetDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    @Override
    public String toString() {
        return "RegistroAsistenciaDTO[" + "id: " + id + ", fechaEntrada: " + fechaEntrada + ", codigoRegistro: " + codigoRegistro + ']';
    }
    
}
