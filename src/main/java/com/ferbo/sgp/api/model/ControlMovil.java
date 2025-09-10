package com.ferbo.sgp.api.model;

import java.util.Date;

import javax.persistence.Basic;
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
@Table(name = "det_control_movil")
public class ControlMovil {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_ctrl_movil")
    private Integer id;

    @Column(name = "nb_token")
	@Size(max = 200)
	@Basic(optional = false)
    private String token;

    @Column(name = "fh_expiracion")
    private Date expiracion;

    @ManyToOne
    @JoinColumn(name = "id_fp_client", nullable = false)
    private Sistema sistema;

	@Column(name = "st_valido")
	private Boolean valido = Boolean.FALSE;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiracion() {
		return expiracion;
	}

	public void setExpiracion(Date expiracion) {
		this.expiracion = expiracion;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControlMovil other = (ControlMovil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ControlMovil [id=" + id + ", expiracion=" + expiracion + "]";
	}

    
}