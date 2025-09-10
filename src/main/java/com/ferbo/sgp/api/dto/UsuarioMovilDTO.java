package com.ferbo.sgp.api.dto;

public class UsuarioMovilDTO {

    private String numeroUsuario;
    private String nombreUsuario;
    private String primerApUsuario;
    private String segundoApUsuario;
    private String puesto;
    private String token;
    private String refreshToken;

    public UsuarioMovilDTO() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPrimerApUsuario() {
        return primerApUsuario;
    }

    public void setPrimerApUsuario(String primerApUsuario) {
        this.primerApUsuario = primerApUsuario;
    }

    public String getSegundoApUsuario() {
        return segundoApUsuario;
    }

    public void setSegundoApUsuario(String segundoApUsuario) {
        this.segundoApUsuario = segundoApUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(String numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
}
