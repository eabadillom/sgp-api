package com.ferbo.sgp.api.model;

import java.math.BigDecimal;
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

/**
 *
 * @author alberto
 */
@Entity
@Table(name = "det_solicitud_permiso")
public class SolicitudPermiso 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;
    
    @NotNull
    @Column(name = "fecha_cap")
    private OffsetDateTime fechaCap;
    
    @Column(name = "fecha_mod")
    private OffsetDateTime fechaMod;
    
    @NotNull
    @Column(name = "fecha_inicio")
    private OffsetDateTime fechaInicio;
    
    @NotNull
    @Column(name = "fecha_fin")
    private OffsetDateTime fechaFin;
    
    @Column(name = "descripcion_rechazo")
    private String descripcionRechazo;
    
    @JoinColumn(name = "aprobada", referencedColumnName = "cd_st_solicitud")
    @ManyToOne()
    private EstatusSolicitud estatusSolicitud;
    
    @JoinColumn(name = "id_tipo_solicitud", referencedColumnName = "id_tipo_solicitud")
    @ManyToOne(optional = false)
    private TipoSolicitud tipoSolicitud;
    
    @JoinColumn(name = "id_empleado_sol", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleado empleadoSol;
    
    @JoinColumn(name = "id_empleado_rev", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleado empleadoRev;
    
    @Column(name = "pc_goce_sueldo")
    private BigDecimal goceSueldo;

    public SolicitudPermiso() {
    }

    public SolicitudPermiso(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public SolicitudPermiso(Integer idSolicitud, OffsetDateTime fechaCap, OffsetDateTime fechaInicio, OffsetDateTime fechaFin) {
        this.idSolicitud = idSolicitud;
        this.fechaCap = fechaCap;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public OffsetDateTime getFechaCap() {
        return fechaCap;
    }

    public void setFechaCap(OffsetDateTime fechaCap) {
        this.fechaCap = fechaCap;
    }

    public OffsetDateTime getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(OffsetDateTime fechaMod) {
        this.fechaMod = fechaMod;
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

    public String getDescripcionRechazo() {
        return descripcionRechazo;
    }

    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public Empleado getEmpleadoSol() {
        return empleadoSol;
    }

    public void setEmpleadoSol(Empleado empleadoSol) {
        this.empleadoSol = empleadoSol;
    }

    public Empleado getEmpleadoRev() {
        return empleadoRev;
    }

    public void setEmpleadoRev(Empleado empleadoRev) {
        this.empleadoRev = empleadoRev;
    }

    public BigDecimal getGoceSueldo() {
        return goceSueldo;
    }

    public void setGoceSueldo(BigDecimal goceSueldo) {
        this.goceSueldo = goceSueldo;
    }

    public EstatusSolicitud getEstatusSolicitud() {
        return estatusSolicitud;
    }

    public void setEstatusSolicitud(EstatusSolicitud estatusSolicitud) {
        this.estatusSolicitud = estatusSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idSolicitud);
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
        final SolicitudPermiso other = (SolicitudPermiso) obj;
        return Objects.equals(this.idSolicitud, other.idSolicitud);
    }

    @Override
    public String toString() {
        return "SolicitudPermiso[" + "idSolicitud=" + idSolicitud + ", fechaCap=" + fechaCap + ", fechaMod=" + fechaMod + 
                ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", descripcionRechazo=" + descripcionRechazo + 
                ", tipoSolicitud=" + tipoSolicitud + ", goceSueldo=" + goceSueldo + '}';
    }
    
}
