package com.ferbo.sgp.api.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alberto
 */
@Entity
@Table(name = "cat_tp_incapacidad")
public class TipoIncapacidad 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "cd_tp_incapacidad")
    private Integer idTpIncapacidad;
    
    @NotNull
    @Column(name = "nb_clave")
    private String clave;
    
    @NotNull
    @Column(name = "nb_descripcion")
    private String descripcion;
    
    @Column(name = "pc_pago")
    @NotNull
    private BigDecimal porcentagePago;
    
    @Column(name = "ct_max_dias")
    @NotNull
    @Basic(optional = false)
    private Integer maxDias;
    
    @Column(name = "ct_semanas_cot")
    @NotNull
    @Basic(optional = false)
    private Integer semanasCotizadas;
    
    @Column(name = "ct_periodo_cot")
    @NotNull
    @Basic(optional = false)
    private Integer periodoCotAnterior;

    public TipoIncapacidad() {
    }
    
    public TipoIncapacidad(Integer idTpIncapacidad) {
        this.idTpIncapacidad = idTpIncapacidad;
    }
    
    public TipoIncapacidad(Integer idTpIncapacidad, String clave) {
        this.idTpIncapacidad = idTpIncapacidad;
        this.clave = clave;
    }

    public Integer getIdTpIncapacidad() {
        return idTpIncapacidad;
    }

    public void setIdTpIncapacidad(Integer idTpIncapacidad) {
        this.idTpIncapacidad = idTpIncapacidad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPorcentagePago() {
        return porcentagePago;
    }

    public void setPorcentagePago(BigDecimal porcentagePago) {
        this.porcentagePago = porcentagePago;
    }

    public Integer getMaxDias() {
        return maxDias;
    }

    public void setMaxDias(Integer maxDias) {
        this.maxDias = maxDias;
    }

    public Integer getSemanasCotizadas() {
        return semanasCotizadas;
    }

    public void setSemanasCotizadas(Integer semanasCotizadas) {
        this.semanasCotizadas = semanasCotizadas;
    }

    public Integer getPeriodoCotAnterior() {
        return periodoCotAnterior;
    }

    public void setPeriodoCotAnterior(Integer periodoCotAnterior) {
        this.periodoCotAnterior = periodoCotAnterior;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.idTpIncapacidad);
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
        final TipoIncapacidad other = (TipoIncapacidad) obj;
        return Objects.equals(this.idTpIncapacidad, other.idTpIncapacidad);
    }

    @Override
    public String toString() 
    {
        return "TipoIncapacidad[" + "idTpIncapacidad=" + idTpIncapacidad + ", clave=" + clave + ", descripcion=" + descripcion + ", porcentagePago=" + porcentagePago + ", maxDias=" + maxDias + ", semanasCotizadas=" + semanasCotizadas + ", periodoCotAnterior=" + periodoCotAnterior + ']';
    }
    
}
