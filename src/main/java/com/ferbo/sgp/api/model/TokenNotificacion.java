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

@Entity
@Table(name = "det_tok_notif")
public class TokenNotificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_tok_notif")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_fp_client", nullable = false)
    private Sistema sistema;

    @Column(name = "cd_token", length = 300)
    private String token;

    @Column(name = "st_valido", nullable = false)
    private Boolean esValido = true;

    // --- Getters y Setters ---
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

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Boolean getEsValido() {
		return esValido;
	}

	public void setEsValido(Boolean esValido) {
		this.esValido = esValido;
	}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TokenNotificacion other = (TokenNotificacion) obj;
        return Objects.equals(this.id, other.id);
    }

        
}

