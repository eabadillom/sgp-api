package com.ferbo.sgp.api.dto;

import java.util.Objects;

/**
 *
 * @author alberto
 */
public class RegistroCompletoDTO extends RegistroParcialDTO
{
    protected String planta;

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final RegistroCompletoDTO other = (RegistroCompletoDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "RegistroCompletoDTO[" + "id=" + id + ", fechaEntrada=" + fechaEntrada + "fechaSalida=" + fechaSalida + ", codigo=" + codigo + 
                ", nombre=" + nombre + ", primerAp=" + primeroAp + ", segundoAp=" + segundoAp + ", planta=" + planta + ']';
    }
    
}
