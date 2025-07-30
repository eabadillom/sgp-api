package com.ferbo.sgp.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "solicitudes.ruta-imagen")
public class RutaImagenProperties {

    private String articulo;

    private String uniforme;

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getUniforme() {
        return uniforme;
    }

    public void setUniforme(String uniforme) {
        this.uniforme = uniforme;
    }

}
