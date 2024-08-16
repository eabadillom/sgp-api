package com.ferbo.sgp.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.response.FotografiaResponse;
import com.ferbo.sgp.api.service.EmpleadoSrv;

@RestController
@RequestMapping("gestion")
@PreAuthorize("hasRole('SYSTEM')")
public class GestionController {
	private static Logger log = LogManager.getLogger(GestionController.class);
	
	@Autowired
	EmpleadoSrv empleadoService;
	
	@GetMapping("/{numero}/fotografia")
	public ResponseEntity<FotografiaResponse> buscarFoto(@PathVariable String numero) {
		ResponseEntity<FotografiaResponse> response = null;
		FotografiaResponse fotografiaResponse = null;
		Empleado empleado = null;
		try {
			log.info("Consultando fotografia por número de empleado: {}", numero);
			empleado = empleadoService.buscarPorNumeroEmpleado(numero);
			
			fotografiaResponse = new FotografiaResponse();
			fotografiaResponse.setFotografia(empleado.getFotografia().getFotografia());
			fotografiaResponse.setNumero(empleado.getNumeroEmpleado());
			fotografiaResponse.setCodigoError(0);
			fotografiaResponse.setMensajeError("Respuesta correcta.");
			
			response = new ResponseEntity<FotografiaResponse>(fotografiaResponse, HttpStatus.OK);
			
		} catch(Exception ex) {
			log.error("Problema para obtener la fotografia del empleado " + numero + "...", ex);
			
			fotografiaResponse = new FotografiaResponse();
			fotografiaResponse.setNumero(numero);
			fotografiaResponse.setCodigoError(1);
			fotografiaResponse.setMensajeError("La informacion solicitada es incorrecta.");
			
			response = new ResponseEntity<FotografiaResponse>(fotografiaResponse, HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	
}
