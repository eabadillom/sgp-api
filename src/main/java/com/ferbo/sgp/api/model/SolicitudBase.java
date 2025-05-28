package com.ferbo.sgp.api.model;

import java.time.LocalDateTime;

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

    @Column(name = "aprobada")
    private Boolean aprobada;

    @Column(name = "fecha_cap", nullable = false)
    private LocalDateTime fechaCaptura;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaModificacion;

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

    public Boolean getAprobada() {
        return aprobada;
    }

    public void setAprobada(Boolean aprobada) {
        this.aprobada = aprobada;
    }

    public LocalDateTime getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(LocalDateTime fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
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
    
}
