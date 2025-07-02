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
        
        @Basic(optional = true)
        @Column(name = "fh_baja")
        private LocalDate fechaBaja;
	
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
        
        @Column(name = "tm_salida")
        private LocalTime horasalida;
	
	@Column(name = "nu_tolerancia")
	private Integer tolerancia;
	
	@OneToOne(mappedBy = "informacionEmpresa")
	private Empleado empleado;
        
        @Basic(optional = true)
        @Column(name = "st_diat_lunes")
        private Boolean diaLunes;

        @Basic(optional = true)
        @Column(name = "st_diat_martes")
        private Boolean diaMartes;

        @Basic(optional = true)
        @Column(name = "st_diat_miercoles")
        private Boolean diaMiercoles;

        @Basic(optional = true)
        @Column(name = "st_diat_jueves")
        private Boolean diaJueves;

        @Basic(optional = true)
        @Column(name = "st_diat_viernes")
        private Boolean diaViernes;

        @Basic(optional = true)
        @Column(name = "st_diat_sabado")
        private Boolean diaSabado;

        @Basic(optional = true)
        @Column(name = "st_diat_domingo")
        private Boolean diaDomingo;
        
        @Basic(optional = true)
        @Column(name = "nb_baja")
        private String motivobaja;

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

        public LocalDate getFechaBaja() {
            return fechaBaja;
        }

        public void setFechaBaja(LocalDate fechaBaja) {
            this.fechaBaja = fechaBaja;
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
        
        public LocalTime getHorasalida() {
            return horasalida;
        }

        public void setHorasalida(LocalTime horasalida) {
            this.horasalida = horasalida;
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
        
        public Boolean getDiaLunes() {
            return diaLunes;
        }

        public void setDiaLunes(Boolean diaLunes) {
            this.diaLunes = diaLunes;
        }

        public Boolean getDiaMartes() {
            return diaMartes;
        }

        public void setDiaMartes(Boolean diaMartes) {
            this.diaMartes = diaMartes;
        }

        public Boolean getDiaMiercoles() {
            return diaMiercoles;
        }

        public void setDiaMiercoles(Boolean diaMiercoles) {
            this.diaMiercoles = diaMiercoles;
        }

        public Boolean getDiaJueves() {
            return diaJueves;
        }

        public void setDiaJueves(Boolean diaJueves) {
            this.diaJueves = diaJueves;
        }

        public Boolean getDiaViernes() {
            return diaViernes;
        }

        public void setDiaViernes(Boolean diaViernes) {
            this.diaViernes = diaViernes;
        }

        public Boolean getDiaSabado() {
            return diaSabado;
        }

        public void setDiaSabado(Boolean diaSabado) {
            this.diaSabado = diaSabado;
        }

        public Boolean getDiaDomingo() {
            return diaDomingo;
        }

        public void setDiaDomingo(Boolean diaDomingo) {
            this.diaDomingo = diaDomingo;
        }

        public String getMotivobaja() {
            return motivobaja;
        }

        public void setMotivobaja(String motivobaja) {
            this.motivobaja = motivobaja;
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
