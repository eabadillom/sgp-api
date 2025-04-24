package com.ferbo.sgp.api.dto;

import java.time.OffsetDateTime;

public class RegistroCompletoDTO extends RegistroParcialDTO {
    
    private RegistroParcialDTO registroParcialDTO;

    private OffsetDateTime fechaSalida;
    private String plantaEmpleado;

    public RegistroCompletoDTO() {
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

    public RegistroParcialDTO getRegistroParcialDTO() {
        return registroParcialDTO;
    }

    public void setRegistroParcialDTO(RegistroParcialDTO registroParcialDTO) {
        this.registroParcialDTO = registroParcialDTO;
    }

    @Override
    public String toString() {
        return "RegistroCompletoDTO [registroParcialDTO=" + registroParcialDTO + ", fechaSalida=" + fechaSalida
                + ", plantaEmpleado=" + plantaEmpleado + "]";
    }

    
}
