package com.ferbo.sgp.api.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author alberto
 */
@Entity
@Table(name = "cat_pais")
public class Pais 
{
    @Id
    @Column(name = "cd_pais")
    private Integer id;
    
    @Column(name = "nb_clave")
    private String clave;

    @Column(name = "nb_pais")
    private String nombre;

    public Pais() {
    }

    public Pais(Integer id, String clave, String nombre) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Pais other = (Pais) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Pais[" + "id=" + id + ", clave=" + clave + ", nombre=" + nombre + ']';
    }
    
}
