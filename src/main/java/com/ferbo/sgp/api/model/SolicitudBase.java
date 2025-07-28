package com.ferbo.sgp.api.model;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SolicitudBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "aprobada")
    private EstatusSolicitud estatusSolicitud;

    @Column(name = "fecha_cap", nullable = false)
    private OffsetDateTime fechaCaptura;

    @Column(name = "fecha_mod")
    private OffsetDateTime fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "id_empleado_sol")
    private Empleado empleadoSolicitante;

    @ManyToOne
    @JoinColumn(name = "id_empleado_rev")
    private Empleado empleadoRevisor;

    @Column(name = "descripcion_rechazo", length = 150)
    private String descripcionRechazo;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    
    public EstatusSolicitud getEstatusSolicitud() {
        return estatusSolicitud;
    }

    public void setEstatusSolicitud(EstatusSolicitud estatusSolicitud) {
        this.estatusSolicitud = estatusSolicitud;
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

    public Empleado getEmpleadoSolicitante() {
        return empleadoSolicitante;
    }

    public void setEmpleadoSolicitante(Empleado empleadoSolicitante) {
        this.empleadoSolicitante = empleadoSolicitante;
    }

    public Empleado getEmpleadoRevisor() {
        return empleadoRevisor;
    }

    public void setEmpleadoRevisor(Empleado empleadoRevisor) {
        this.empleadoRevisor = empleadoRevisor;
    }

    public String getDescripcionRechazo() {
        return descripcionRechazo;
    }

    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final SolicitudBase other = (SolicitudBase) obj;
        return Objects.equals(this.id, other.id);
    }

    
}
