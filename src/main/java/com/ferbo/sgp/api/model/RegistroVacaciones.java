package com.ferbo.sgp.api.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "det_registro_vacaciones")
public class RegistroVacaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reg_vacaciones")
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_registro")
    @Basic(optional = false)
    private RegistroAsistencia registroAsistencia;

    @ManyToOne
    @JoinColumn(name = "id_vacaciones")
    @Basic(optional = false)
    private Vacaciones vacaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final RegistroVacaciones other = (RegistroVacaciones) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "}";
    }

    public RegistroAsistencia getRegistroAsistencia() {
        return registroAsistencia;
    }

    public void setRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        this.registroAsistencia = registroAsistencia;
    }

    public Vacaciones getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(Vacaciones vacaciones) {
        this.vacaciones = vacaciones;
    }
}
