package com.ferbo.sgp.api.model.response;

import com.ferbo.sgp.api.dto.RegistroCompletoDTO;

/**
 *
 * @author alberto
 */
public class RegistroAsistenciaCompletoResponse extends RegistroCompletoDTO
{
    protected Integer codigoError = null;
    protected String mensajeError = null;

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
    
}
