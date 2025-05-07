package com.ferbo.sgp.api.dto;

import java.math.BigDecimal;

/**
 *
 * @author alberto
 */
public class SolicitudPermisoDetalleDTO extends SolicitudPermisoDTO
{
    private String descripcionRechazo;

    public SolicitudPermisoDetalleDTO() 
    {
    }

    public String getDescripcionRechazo() {
        return descripcionRechazo;
    }

    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }

    @Override
    public String toString() {
        return "SolicitudPermisoDetalletDTO[" + "descripcionRechazo=" + descripcionRechazo + ']';
    }
    
}
