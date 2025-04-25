package com.ferbo.sgp.api.model.response;

import com.ferbo.sgp.api.dto.RegistroParcialDTO;

/**
 *
 * @author alberto
 */
public class RegistroAsistenciaParcialResponse extends RegistroParcialDTO
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
    
    public static RegistroAsistenciaParcialResponse from(RegistroParcialDTO dto) {
        RegistroAsistenciaParcialResponse resp = new RegistroAsistenciaParcialResponse();
        resp.setId(dto.getId());
        resp.setFechaEntrada(dto.getFechaEntrada());
        resp.setFechaSalida(dto.getFechaSalida());
        resp.setCodigo(dto.getCodigo());
        resp.setNombre(dto.getNombre());
        resp.setPrimeroAp(dto.getPrimeroAp());
        resp.setSegundoAp(dto.getSegundoAp());
        return resp;
    }
    
}
