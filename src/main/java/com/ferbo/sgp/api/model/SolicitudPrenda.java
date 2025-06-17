package com.ferbo.sgp.api.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "det_solicitud_prenda")
public class SolicitudPrenda extends SolicitudBase {

    @ManyToOne
    @JoinColumn(name = "id_prenda")
    private Prenda prenda;

    @ManyToOne
    @JoinColumn(name = "id_talla")
    private Talla talla;

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }
    
}
