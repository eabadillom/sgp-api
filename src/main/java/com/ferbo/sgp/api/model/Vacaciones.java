
package com.ferbo.sgp.api.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author julio
 */

@Entity
@Table(name = "det_vacaciones")
public class Vacaciones implements Serializable {
    
    private static final long serialVersionUID = -2632823297112045342L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacaciones")
    private Integer id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    private Empleado empleado;
    
    @Basic(optional = false)
    @Column(name = "fh_inicio")
    private OffsetDateTime fechaInicio;
    
    @Basic(optional = false)
    @Column(name = "fh_fin")
    private OffsetDateTime fechaFin;
    
    @Basic(optional = false)
    @Column(name = "nu_dias_totales")
    private Integer diasTotales;
    
    @Basic(optional = false)
    @Column(name = "nu_dias_tomados", nullable = false)
    private Integer diasTomados;
    
    @Column(name = "st_prima_pagada", nullable = false)
    private Boolean primaPagada = Boolean.FALSE;

    @Column(name = "st_dias_pend_pagados", nullable = false)
    private Boolean diasPendientesPagados = Boolean.FALSE;
    
    @Basic(optional = false)
    @Column(name = "nu_dias_pagados")
    private Integer diasPagados;
    
    @OneToMany(mappedBy = "vacaciones")
    private List<RegistroVacaciones> registroVacaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public OffsetDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(OffsetDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public OffsetDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(OffsetDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDiasTotales() {
        return diasTotales;
    }

    public void setDiasTotales(Integer diasTotales) {
        this.diasTotales = diasTotales;
    }

    public Integer getDiasTomados() {
        return diasTomados;
    }

    public void setDiasTomados(Integer diasTomados) {
        this.diasTomados = diasTomados;
    }

    public Boolean getPrimaPagada() {
        return primaPagada;
    }

    public void setPrimaPagada(Boolean primaPagada) {
        this.primaPagada = primaPagada;
    }

    public Boolean getDiasPendientesPagados() {
        return diasPendientesPagados;
    }

    public void setDiasPendientesPagados(Boolean diasPendientesPagados) {
        this.diasPendientesPagados = diasPendientesPagados;
    }

    public Integer getDiasPagados() {
        return diasPagados;
    }

    public void setDiasPagados(Integer diasPagados) {
        this.diasPagados = diasPagados;
    }

    public List<RegistroVacaciones> getRegistroVacaciones() {
        return registroVacaciones;
    }

    public void setRegistroVacaciones(List<RegistroVacaciones> registroVacaciones) {
        this.registroVacaciones = registroVacaciones;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Vacaciones other = (Vacaciones) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Vacaciones{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", diasTotales=" + diasTotales + ", diasTomados=" + diasTomados + ", primaPagada=" + primaPagada + ", diasPendientesPagados=" + diasPendientesPagados + ", diasPagados=" + diasPagados + '}';
    }
    
    
}
