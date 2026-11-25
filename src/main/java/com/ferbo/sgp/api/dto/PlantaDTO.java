package com.ferbo.sgp.api.dto;

public class PlantaDTO 
{
    private Integer id;
    private String descripcion;

    public PlantaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "PlantaDTO[" + "id: " + id + ", descripcion: " + descripcion + ']';
    }
    
}
