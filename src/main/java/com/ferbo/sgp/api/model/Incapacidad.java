package com.ferbo.sgp.api.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 *
 * @author alberto
 */
@Entity
@Table(name = "det_incapacidad")
public class Incapacidad 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_incapacidad")
    private Integer idIncapacidad;
    
    @ManyToOne()
    @JoinColumn(name = "id_empleado_inc", referencedColumnName = "id_empleado")
    private Empleado idEmpleadoInc;
    
    @ManyToOne()
    @JoinColumn(name = "id_empleado_rev", referencedColumnName = "id_empleado")
    private Empleado idEmpleadoRev;
    
    @ManyToOne()
    @JoinColumn(name = "cd_tp_incapacidad", referencedColumnName = "cd_tp_incapacidad")
    private TipoIncapacidad tipoIncapacidad;
    
    @ManyToOne()
    @JoinColumn(name = "cd_cont_incapacidad", referencedColumnName = "cd_control_incapacidad")
    private ControlIncapacidad controlIncapacidad;
    
    @Null
    @ManyToOne()
    @JoinColumn(name = "cd_secuela_rt", referencedColumnName = "cd_sec_riesgo_trabajo")
    private RiesgoTrabajo secuelaRiesgoTrabajo;
    
    @Null
    @ManyToOne()
    @JoinColumn(name = "cd_tp_riesgo", referencedColumnName = "cd_tp_riesgo")
    private TipoRiesgo tipoRiesgo;
    
    @NotNull
    @Column(name = "nb_folio")
    private String folio;
    
    @NotNull
    @Column(name = "nu_dias_autorizados")
    private Integer diasAutorizados;
    
    @NotNull
    @Column(name = "fh_inicio")
    private OffsetDateTime fechaInicio;
    
    @NotNull
    @Column(name = "fh_fin")
    private OffsetDateTime fechaFin;
    
    @NotNull
    @Column(name = "fh_captura")
    private OffsetDateTime fechaCaptura;
    
    @NotNull
    @Column(name = "tx_descripcion")
    private String descripcion;
    
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "cd_estatus", referencedColumnName = "cd_estatus_inc")
    private EstatusIncapacidad estatusSolicitud;

    public Incapacidad() {
    }

    public Incapacidad(Integer idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public Integer getIdIncapacidad() {
        return idIncapacidad;
    }

    public void setIdIncapacidad(Integer idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public Empleado getIdEmpleadoInc() {
        return idEmpleadoInc;
    }

    public void setIdEmpleadoInc(Empleado idEmpleadoInc) {
        this.idEmpleadoInc = idEmpleadoInc;
    }

    public Empleado getIdEmpleadoRev() {
        return idEmpleadoRev;
    }

    public void setIdEmpleadoRev(Empleado idEmpleadoRev) {
        this.idEmpleadoRev = idEmpleadoRev;
    }

    public TipoIncapacidad getTipoIncapacidad() {
        return tipoIncapacidad;
    }

    public void setTipoIncapacidad(TipoIncapacidad tipoIncapacidad) {
        this.tipoIncapacidad = tipoIncapacidad;
    }

    public ControlIncapacidad getControlIncapacidad() {
        return controlIncapacidad;
    }

    public void setControlIncapacidad(ControlIncapacidad controlIncapacidad) {
        this.controlIncapacidad = controlIncapacidad;
    }

    public RiesgoTrabajo getSecuelaRiesgoTrabajo() {
        return secuelaRiesgoTrabajo;
    }

    public void setSecuelaRiesgoTrabajo(RiesgoTrabajo secuelaRiesgoTrabajo) {
        this.secuelaRiesgoTrabajo = secuelaRiesgoTrabajo;
    }

    public TipoRiesgo getTipoRiesgo() {
        return tipoRiesgo;
    }

    public void setTipoRiesgo(TipoRiesgo tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Integer getDiasAutorizados() {
        return diasAutorizados;
    }

    public void setDiasAutorizados(Integer diasAutorizados) {
        this.diasAutorizados = diasAutorizados;
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

    public OffsetDateTime getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(OffsetDateTime fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstatusIncapacidad getEstatusSolicitud() {
        return estatusSolicitud;
    }

    public void setEstatusSolicitud(EstatusIncapacidad estatusSolicitud) {
        this.estatusSolicitud = estatusSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idIncapacidad);
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
        final Incapacidad other = (Incapacidad) obj;
        return Objects.equals(this.idIncapacidad, other.idIncapacidad);
    }

    @Override
    public String toString() {
        return "Incapacidad[" + "idIncapacidad=" + idIncapacidad + ", idEmpleadoInc=" + idEmpleadoInc.getIdEmpleado() + ", folio=" + folio + ", diasAutorizados=" + diasAutorizados + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", fechaCaptura=" + fechaCaptura + ", descripcion=" + descripcion + ']';
    }
    
}
