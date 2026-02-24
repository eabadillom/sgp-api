package com.ferbo.sgp.api.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    
    @Column(name = "correo")
    private String correo;
    
    @Column(name = "activo")
    private Integer activo;
    
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_empleado_foto")
//    private Fotografia fotografia;
    
    @OneToOne
    @JoinColumn(name = "id_empleado_empresa")
    private InformacionEmpresa informacionEmpresa;
    
    @OneToOne(mappedBy = "empleado", fetch = FetchType.LAZY)
    private Biometrico biometrico;
    
    @OneToOne(mappedBy = "empleado", orphanRemoval = true)
    private EmpleadoConfiguracion empleadoConfiguracion;
    
    @Override
	public int hashCode() {
    	if(this.idEmpleado == null)
    		return System.identityHashCode(this);
		return Objects.hash(idEmpleado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(idEmpleado, other.idEmpleado);
	}

	@Override
	public String toString() {
		return "Empleado [idEmpleado=" + idEmpleado + ", numeroEmpleado=" + numeroEmpleado + ", nombre=" + nombre
				+ ", primeroAp=" + primeroAp + ", segundoAp=" + segundoAp + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaRegistro=" + fechaRegistro + ", fechaModificacion=" + fechaModificacion + ", curp=" + curp
				+ ", correo=" + correo + ", activo=" + activo + "]";
	}
    
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

//	public Fotografia getFotografia() {
//		return fotografia;
//	}
//
//	public void setFotografia(Fotografia fotografia) {
//		this.fotografia = fotografia;
//	}
	
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
        
        public EmpleadoConfiguracion getEmpleadoConfiguracion() {
            return empleadoConfiguracion;
        }

        public void setEmpleadoConfiguracion(EmpleadoConfiguracion empleadoConfiguracion) {
            this.empleadoConfiguracion = empleadoConfiguracion;
        }
}
