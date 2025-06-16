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

@Entity
@Table(name = "det_incidencia")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencia")
    private Integer id;

    @Column(name = "visible")
    private Short visible;

    @Column(name = "fecha_cap")
    private OffsetDateTime fechaCaptura;

    @Column(name = "fecha_mod")
    private OffsetDateTime fechaModificacion;

    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne()
    private TipoIncidencia tipo;

    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne()
    private EstatusIncidencia estatus;
    
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne()
    private Empleado empleadoSol;

    @JoinColumn(name = "id_empleado_rev", referencedColumnName = "id_empleado")
    @ManyToOne()
    private Empleado empladoRevisa;
    
    @JoinColumn(name = "id_sol_permiso", referencedColumnName = "id_solicitud")
    @ManyToOne()
    private SolicitudPermiso solicitudPermiso;

    // private SolicitudArticulo solicitudArticulo;
    // private SolicitudPrenda solicitudPrenda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getVisible() {
        return visible;
    }

    public void setVisible(Short visible) {
        this.visible = visible;
    }

    public OffsetDateTime getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(OffsetDateTime fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public OffsetDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(OffsetDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public TipoIncidencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoIncidencia tipo) {
        this.tipo = tipo;
    }

    public EstatusIncidencia getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusIncidencia estatus) {
        this.estatus = estatus;
    }

    public Empleado getEmpleadoSol() {
        return empleadoSol;
    }

    public void setEmpleadoSol(Empleado empleadoSol) {
        this.empleadoSol = empleadoSol;
    }

    public Empleado getEmpladoRevisa() {
        return empladoRevisa;
    }

    public void setEmpladoRevisa(Empleado empladoRevisa) {
        this.empladoRevisa = empladoRevisa;
    }

    public SolicitudPermiso getSolicitudPermiso() {
        return solicitudPermiso;
    }

    public void setSolicitudPermiso(SolicitudPermiso solicitudPermiso) {
        this.solicitudPermiso = solicitudPermiso;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Incidencia other = (Incidencia) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Incidencia [id=" + id + ", visible=" + visible + ", fechaCaptura=" + fechaCaptura
                + ", fechaModificacion=" + fechaModificacion + "]";
    }
    
}