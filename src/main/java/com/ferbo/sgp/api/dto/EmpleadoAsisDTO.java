package com.ferbo.sgp.api.dto;

import java.util.List;
import java.util.Objects;

public class EmpleadoAsisDTO 
{
    private Integer id;
    private String nombre;
    private String primerAp;
    private String segundoAp;
    private List<RegistroAsistenciaDTO> asistencia;

    public EmpleadoAsisDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerAp() {
        return primerAp;
    }

    public void setPrimerAp(String primerAp) {
        this.primerAp = primerAp;
    }

    public String getSegundoAp() {
        return segundoAp;
    }

    public void setSegundoAp(String segundoAp) {
        this.segundoAp = segundoAp;
    }

    public List<RegistroAsistenciaDTO> getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(List<RegistroAsistenciaDTO> asistencia) {
        this.asistencia = asistencia;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final EmpleadoAsisDTO other = (EmpleadoAsisDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "EmpleadoAsisDTO[" + "idEmpleado: " + id + ", nombre: " + nombre + ", primerApEmpleado: " + primerAp + ", segundoApEmpleado: " + segundoAp + ']';
    }
    
}
