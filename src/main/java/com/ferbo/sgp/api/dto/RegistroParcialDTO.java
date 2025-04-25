package com.ferbo.sgp.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 *
 * @author alberto
 */
public class RegistroParcialDTO 
{
    protected Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    protected OffsetDateTime fechaEntrada;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    protected OffsetDateTime fechaSalida;
    protected String codigo;
    protected String nombre;
    protected String primeroAp;
    protected String segundoAp;
    
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
    
    public OffsetDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(OffsetDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimeroAp() {
        return primeroAp;
    }

    public void setPrimeroAp(String primeroAp) {
        this.primeroAp = primeroAp;
    }

    public String getSegundoAp() {
        return segundoAp;
    }

    public void setSegundoAp(String segundoAp) {
        this.segundoAp = segundoAp;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final RegistroParcialDTO other = (RegistroParcialDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "RegistroParcialDTO[" + "id=" + id + ", fechaEntrada=" + fechaEntrada + "fechaSalida=" + fechaSalida + ", codigo=" + codigo + ", "
                + "nombre=" + nombre + ", primerAp=" + primeroAp + ", segundoAp=" + segundoAp + ']';
    }
    
}
