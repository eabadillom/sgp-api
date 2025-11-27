package com.ferbo.sgp.api.dto;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author alberto
 */
public class IncapacidadDetalleDTO 
{
    private Integer idIncapacidad;
    private String nombreInc;
    private String primerApInc;
    private String segundoApInc;
    private String empleadoRev;
    private String tipoIncapacidad;
    private String controlIncapacidad;
    private String riesgoTrabajo;
    private String tipoRiesgo;
    private String folio;
    private Integer diasAutorizados;
    private String descripcion;
    private List<OffsetDateTime> periodo;
    private String estatusIncapacidad;

    public IncapacidadDetalleDTO() {
    }

    public IncapacidadDetalleDTO(Integer idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public Integer getIdIncapacidad() {
        return idIncapacidad;
    }

    public void setIdIncapacidad(Integer idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public String getNombreInc() {
        return nombreInc;
    }

    public void setNombreInc(String nombreInc) {
        this.nombreInc = nombreInc;
    }

    public String getPrimerApInc() {
        return primerApInc;
    }

    public void setPrimerApInc(String primerApInc) {
        this.primerApInc = primerApInc;
    }

    public String getSegundoApInc() {
        return segundoApInc;
    }

    public void setSegundoApInc(String segundoApInc) {
        this.segundoApInc = segundoApInc;
    }

    public String getEmpleadoRev() {
        return empleadoRev;
    }

    public void setEmpleadoRev(String empleadoRev) {
        this.empleadoRev = empleadoRev;
    }

    public String getTipoIncapacidad() {
        return tipoIncapacidad;
    }

    public void setTipoIncapacidad(String tipoIncapacidad) {
        this.tipoIncapacidad = tipoIncapacidad;
    }

    public String getControlIncapacidad() {
        return controlIncapacidad;
    }

    public void setControlIncapacidad(String controlIncapacidad) {
        this.controlIncapacidad = controlIncapacidad;
    }

    public String getRiesgoTrabajo() {
        return riesgoTrabajo;
    }

    public void setRiesgoTrabajo(String riesgoTrabajo) {
        this.riesgoTrabajo = riesgoTrabajo;
    }

    public String getTipoRiesgo() {
        return tipoRiesgo;
    }

    public void setTipoRiesgo(String tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Integer getDiasAutorizados() {
        return diasAutorizados;
    }

    public void setDiasAutorizados(Integer diasAutorizados) {
        this.diasAutorizados = diasAutorizados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<OffsetDateTime> getPeriodo() {
        return periodo;
    }

    public void setPeriodo(List<OffsetDateTime> periodo) {
        this.periodo = periodo;
    }

    public String getEstatusIncapacidad() {
        return estatusIncapacidad;
    }

    public void setEstatusIncapacidad(String estatusIncapacidad) {
        this.estatusIncapacidad = estatusIncapacidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idIncapacidad);
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
        final IncapacidadDetalleDTO other = (IncapacidadDetalleDTO) obj;
        return Objects.equals(this.idIncapacidad, other.idIncapacidad);
    }

    @Override
    public String toString() {
        return "IncapacidadDetalleDTO[" + "idIncapacidad=" + idIncapacidad + ", nombreInc=" + nombreInc + ", primerApInc=" + primerApInc 
                + ", segundoApInc=" + segundoApInc + ", idEmpleadoRev=" + empleadoRev + ", folio=" + folio + ", diasAutorizados=" 
                + diasAutorizados + ", descripcion=" + descripcion + ']';
    }
    
}
