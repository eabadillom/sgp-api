package com.ferbo.sgp.api.dto;

import java.util.Objects;

/**
 *
 * @author alberto
 */
public class TipoIncapacidadDTO 
{
    private Integer idTpIncapacidad;
    private String clave;
    private String descripcion;
    private Integer maxDias;

    public TipoIncapacidadDTO() {
    }

    public TipoIncapacidadDTO(Integer idTpIncapacidad) {
        this.idTpIncapacidad = idTpIncapacidad;
    }

    public Integer getIdTpIncapacidad() {
        return idTpIncapacidad;
    }

    public void setIdTpIncapacidad(Integer idTpIncapacidad) {
        this.idTpIncapacidad = idTpIncapacidad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMaxDias() {
        return maxDias;
    }

    public void setMaxDias(Integer maxDias) {
        this.maxDias = maxDias;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.idTpIncapacidad);
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
        final TipoIncapacidadDTO other = (TipoIncapacidadDTO) obj;
        return Objects.equals(this.idTpIncapacidad, other.idTpIncapacidad);
    }

    @Override
    public String toString() {
        return "TipoIncapacidadDTO[" + "idTpIncapacidad=" + idTpIncapacidad + ", clave=" + clave + ", descripcion=" + descripcion + ", maxDias=" + maxDias + ']';
    }
    
    
    
}
