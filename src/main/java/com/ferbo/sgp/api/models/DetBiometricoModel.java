package com.ferbo.sgp.api.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="det_biometrico")
public class DetBiometricoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_biometrico",unique = true)
    private Integer idBiometrico;
    
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_captura")
    private Date fechaCaptura;
    
    @Column(name = "activo")
    private Integer activo;
    
    @Column(name = "huella")
    private String huella;
    
    @Column(name = "huella2")
    private String huella2;
    
    
    public String getHuella2() {
        return huella2;
    }
    public void setHuella2(String huella2) {
        this.huella2 = huella2;
    }
    public String getHuella() {
        return huella;
    }
    public void setHuella(String huella) {
        this.huella = huella;
    }
    
    public Integer getActivo() {
        return activo;
    }
    public void setActivo(Integer activo) {
        this.activo = activo;
    }
    
    public Integer getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public Date getFechaCaptura() {
        return fechaCaptura;
    }
    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }
    
    public Integer getIdBiometrico() {
        return idBiometrico;
    }
    public void setIdBiometrico(Integer idBiometrico) {
        this.idBiometrico = idBiometrico;
    }
    
}

