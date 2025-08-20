package com.ferbo.sgp.api.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "det_registro")
public class RegistroAsistencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registro")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
	private Empleado empleado;
	
	@Column(name = "fecha_entrada")
	private OffsetDateTime fechaEntrada;
	
	@Column(name = "fecha_salida")
	private OffsetDateTime fechaSalida;
	
	@ManyToOne
	@JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
	private EstadoRegistro status;
        
    @OneToOne(mappedBy = "registroAsistencia", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private RegistroVacaciones registroVacaciones;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public OffsetDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(OffsetDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public OffsetDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(OffsetDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public EstadoRegistro getStatus() {
		return status;
	}

	public void setStatus(EstadoRegistro status) {
		this.status = status;
	}

        public RegistroVacaciones getRegistroVacaciones() {
            return registroVacaciones;
        }

        public void setRegistroVacaciones(RegistroVacaciones registroVacaciones) {
            this.registroVacaciones = registroVacaciones;
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
		RegistroAsistencia other = (RegistroAsistencia) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"fechaEntrada\":\"" + fechaEntrada + "\", \"fechaSalida\":\"" + fechaSalida
				+ "\"}";
	}
	
}
