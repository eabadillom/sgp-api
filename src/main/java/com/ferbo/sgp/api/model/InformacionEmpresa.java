package com.ferbo.sgp.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "inf_empleado_empresa")
public class InformacionEmpresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado_empresa")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil; 
	
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "id_planta")
	@Basic(optional = true)
	private Planta planta;
	
	@ManyToOne
	@JoinColumn(name = "id_area")
	private Area area;
	
	@ManyToOne
	@JoinColumn(name = "id_puesto")
	private Puesto puesto;
	
	@ManyToOne
	@JoinColumn(name = "cd_contrato")
	private TipoContrato tipoContrato;
	
	@ManyToOne
	@JoinColumn(name = "cd_jornada")
	private TipoJornada tipoJornada;
	
	@ManyToOne
	@JoinColumn(name = "cd_tp_regimen")
	private TipoRegimen tipoRegimen;
	
	@Column(name = "fh_ingreso")
	@Basic(optional = false)
	private LocalDate fechaIngreso;
	
	@Column(name = "nu_nss")
	@Size(max = 45)
	private String nss;
	
	@Column(name = "nb_rfc")
	@Size(max = 15)
	private String rfc;
	
	@Column(name = "nu_salario_diario")
	private BigDecimal salarioDiario;
	
	@Column(name = "tm_entrada")
	private LocalTime horaEntrada;
	
	@Column(name = "nu_tolerancia")
	private Integer tolerancia;
	
	@OneToOne(mappedBy = "informacionEmpresa")
	private Empleado empleado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public TipoJornada getTipoJornada() {
		return tipoJornada;
	}

	public void setTipoJornada(TipoJornada tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

	public TipoRegimen getTipoRegimen() {
		return tipoRegimen;
	}

	public void setTipoRegimen(TipoRegimen tipoRegimen) {
		this.tipoRegimen = tipoRegimen;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public BigDecimal getSalarioDiario() {
		return salarioDiario;
	}

	public void setSalarioDiario(BigDecimal salarioDiario) {
		this.salarioDiario = salarioDiario;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Integer getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(Integer tolerancia) {
		this.tolerancia = tolerancia;
	}
	
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacionEmpresa other = (InformacionEmpresa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"fechaIngreso\":\"" + fechaIngreso + "\", \"nss\":\"" + nss + "\", \"rfc\":\""
				+ rfc + "\", \"salarioDiario\":\"" + salarioDiario + "\", \"horaEntrada\":\"" + horaEntrada
				+ "\", \"tolerancia\":\"" + tolerancia + "\"}";
	}
}
