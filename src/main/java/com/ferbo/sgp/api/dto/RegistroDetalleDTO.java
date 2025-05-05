package com.ferbo.sgp.api.dto;

import java.time.OffsetDateTime;

public class RegistroDetalleDTO extends RegistroDTO {
    
    private RegistroDTO registroParcialDTO;

    private OffsetDateTime fechaSalida;
    private String plantaEmpleado;

    public RegistroDetalleDTO() {
    }

    public OffsetDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(OffsetDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getPlantaEmpleado() {
        return plantaEmpleado;
    }

    public void setPlantaEmpleado(String plantaEmpleado) {
        this.plantaEmpleado = plantaEmpleado;
    }

    public RegistroDTO getRegistroParcialDTO() {
        return registroParcialDTO;
    }

    public void setRegistroParcialDTO(RegistroDTO registroParcialDTO) {
        this.registroParcialDTO = registroParcialDTO;
    }

    @Override
    public String toString() {
        return "RegistroCompletoDTO [registroParcialDTO=" + registroParcialDTO + ", fechaSalida=" + fechaSalida
                + ", plantaEmpleado=" + plantaEmpleado + "]";
    }

    
}
