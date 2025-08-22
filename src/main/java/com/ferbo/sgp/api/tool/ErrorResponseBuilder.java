package com.ferbo.sgp.api.tool;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ferbo.sgp.api.model.response.MovilResponse;

public class ErrorResponseBuilder {
    
    public static ResponseEntity<MovilResponse> construirErrorMovil(HttpStatus status, String tipoError, Exception ex){
        MovilResponse movilResponse = new MovilResponse();
        
        movilResponse.setCodigoError(status.value());
        movilResponse.setTipoError(tipoError);
        movilResponse.setMensajeError(ex.getMessage());
        movilResponse.setTiempoError(OffsetDateTime.now());
        
        return new ResponseEntity<>(movilResponse, status);
    }

}
