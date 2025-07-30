package com.ferbo.sgp.api.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alberto
 */
@Entity
@Table(name = "det_empleado_conf")
public class EmpleadoConfiguracion 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_empleado_conf")
    private Integer idEmpleadoConf;
    
    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(name = "cd_empleado")
    private Empleado empleado;
    
    @Column(name = "st_goce_sueldo")
    private Boolean goceSueldo;
    
    @Column(name = "st_asistencia_nl")
    private Boolean asistenciaDiaNoLaboral;

    public EmpleadoConfiguracion() {
    }

    public Integer getIdEmpleadoConf() {
        return idEmpleadoConf;
    }

    public void setIdEmpleadoConf(Integer idEmpleadoConf) {
        this.idEmpleadoConf = idEmpleadoConf;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Boolean getGoceSueldo() {
        return goceSueldo;
    }

    public void setGoceSueldo(Boolean goceSueldo) {
        this.goceSueldo = goceSueldo;
    }

    public Boolean getAsistenciaDiaNoLaboral() {
        return asistenciaDiaNoLaboral;
    }

    public void setAsistenciaDiaNoLaboral(Boolean asistenciaDiaNoLaboral) {
        this.asistenciaDiaNoLaboral = asistenciaDiaNoLaboral;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idEmpleadoConf);
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
        final EmpleadoConfiguracion other = (EmpleadoConfiguracion) obj;
        return Objects.equals(this.idEmpleadoConf, other.idEmpleadoConf);
    }

    @Override
    public String toString() {
        return "EmpleadoConfiguracion[" + "idEmpleadoConf=" + idEmpleadoConf + ", empleado=" + empleado 
                + ", goceSueldo=" + goceSueldo + ", asistenciaDiaNoLaboral=" + asistenciaDiaNoLaboral + ']';
    }
    
}
