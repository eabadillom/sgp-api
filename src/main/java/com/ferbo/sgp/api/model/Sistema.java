package com.ferbo.sgp.api.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "det_fp_client")
public class Sistema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fp_client")
	private Integer id;
	
	@Column(name = "nb_client", unique = true)
	@Size(max=50)
	private String nombre;
	
	@Column(name = "nb_password")
	@Size(max=150)
	private String password;
	
	@Column(name = "nb_rol")
	@Size(max=50)
	private String rol;
	
	@ManyToOne
	@JoinColumn(name = "id_planta")
	private Planta planta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
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
		Sistema other = (Sistema) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Sistema [id=" + id + ", nombre=" + nombre + ", rol=" + rol + "]";
	}
}
