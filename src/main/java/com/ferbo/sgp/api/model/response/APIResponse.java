package com.ferbo.sgp.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author alberto
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> 
{
    private T datos = null;
    private Integer codigoError = null;
    private String mensajeError = null;
    
    public APIResponse(T datos)
    {
        this.datos = datos;
    }

    public APIResponse(T datos, Integer codigoError, String mensajeError) 
    {
        this.datos = datos;
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
    }

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

    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }
    
    public static <T> APIResponse<T> ok(T datos) 
    {
        return new APIResponse<>(datos, null, null);
    }

    public static <T> APIResponse<T> error(String mensaje, int codigoError) 
    {
        return new APIResponse<>(null, codigoError, mensaje);
    }
    
}
