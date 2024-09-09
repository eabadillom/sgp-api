package com.ferbo.sgp.api.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="det_biometrico")
public class Biometrico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_biometrico",unique = true)
    private Integer idBiometrico;
    
    @OneToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_captura")
    private Date fechaCaptura;
    
    @Column(name = "activo")
    private Integer activo;
    
    @Column(name = "huella")
    private String huella1;
    
    @Column(name = "huella2")
    private String huella2;
    
    
    public String getHuella2() {
        return huella2;
    }
    public void setHuella2(String huella) {
        this.huella2 = huella;
    }
    public String getHuella1() {
        return huella1;
    }
    public void setHuella1(String huella) {
        this.huella1 = huella;
    }
    
    public Integer getActivo() {
        return activo;
    }
    public void setActivo(Integer activo) {
        this.activo = activo;
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }
    public void setIdEmpleado(Empleado empleado) {
        this.empleado = empleado;
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
	@Override
	public int hashCode() {
		return Objects.hash(idBiometrico);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Biometrico other = (Biometrico) obj;
		return Objects.equals(idBiometrico, other.idBiometrico);
	}
	@Override
	public String toString() {
		return "Biometrico [idBiometrico=" + idBiometrico + ", fechaCaptura=" + fechaCaptura + ", activo=" + activo
				+ "]";
	}
}

