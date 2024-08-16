package com.ferbo.sgp.api.model;

import java.util.Date;

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
@Table(name = "det_empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_empleado", unique = true)
    private Integer idEmpleado;

    @Column(name = "num_empleado")
    private String numeroEmpleado;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "primer_ap")
    private String primeroAp;
    
    @Column(name = "segundo_ap")
    private String segundoAp;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;
    
    @Column(name = "curp")
    private String curp;
    
    @Column(name = "rfc")
    private String rfc;
    
    @Column(name = "correo")
    private String correo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;
    
    @Column(name = "nss")
    private String nss;
    
    @Column(name = "activo")
    private Integer activo;
    
    @OneToOne
    @JoinColumn(name = "id_empleado_foto")
    private Fotografia fotografia;
    
    @OneToOne
    @JoinColumn(name = "id_empleado_empresa")
    private InformacionEmpresa informacionEmpresa;
    
    @OneToOne(mappedBy = "empleado")
    private Biometrico biometrico;
    
	public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numEmpleado) {
        this.numeroEmpleado = numEmpleado;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getPrimeroAp() {
        return primeroAp;
    }

    public void setPrimeroAp(String primeroAp) {
        this.primeroAp = primeroAp;
    }
    
    public String getSegundoAp() {
        return segundoAp;
    }

    public void setSegundoAp(String segundoAp) {
        this.segundoAp = segundoAp;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

	public Fotografia getFotografia() {
		return fotografia;
	}

	public void setFotografia(Fotografia fotografia) {
		this.fotografia = fotografia;
	}
	
	public InformacionEmpresa getInformacionEmpresa() {
		return informacionEmpresa;
	}

	public void setInformacionEmpresa(InformacionEmpresa informacionEmpresa) {
		this.informacionEmpresa = informacionEmpresa;
	}

	public Biometrico getBiometrico() {
		return biometrico;
	}

	public void setBiometrico(Biometrico biometrico) {
		this.biometrico = biometrico;
	}
}
