package com.ferbo.sgp.api.model;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author alberto
 */
@Entity
@Table(name = "cat_dia_no_laboral")
public class DiaNoLaboral 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fecha")
    private Integer id;

    @Column(name = "fh_fecha")
    private OffsetDateTime fecha;

    @Column(name = "nb_descripcion")
    private String descripcion;

    @JoinColumn(name = "cd_pais", referencedColumnName = "cd_pais")
    @ManyToOne
    private Pais pais;

    @Column(name = "st_oficial")
    private Boolean oficial;

    public DiaNoLaboral() {
    }

    public DiaNoLaboral(Integer id, OffsetDateTime fecha, String descripcion, Pais pais, Boolean oficial) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.pais = pais;
        this.oficial = oficial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Boolean getOficial() {
        return oficial;
    }

    public void setOficial(Boolean oficial) {
        this.oficial = oficial;
    }
    
}
