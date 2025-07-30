package com.ferbo.sgp.api.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "det_solicitud_articulo")
public class SolicitudArticulo extends SolicitudBase{

    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    
}
