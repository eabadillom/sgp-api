package com.ferbo.sgp.api.dto;

public class EstatusAsistenciaDTO 
{
    private String codigo;
    private String descripcion;

    public EstatusAsistenciaDTO() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "EstatusAsistenciaDTO[" + "codigo: " + codigo + ", descripcion: " + descripcion + ']';
    }
    
}
