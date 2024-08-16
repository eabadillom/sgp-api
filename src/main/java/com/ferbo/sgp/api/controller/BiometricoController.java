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

import com.ferbo.sgp.api.model.Biometrico;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.response.BiometricoResponse;
import com.ferbo.sgp.api.model.response.FotografiaResponse;
import com.ferbo.sgp.api.service.BiometricoSrv;
import com.ferbo.sgp.api.service.EmpleadoSrv;

@RestController
@RequestMapping("/biometrico")
@PreAuthorize("hasRole('FP_CLIENT')")
public class BiometricoController {
	
	private static Logger log = LogManager.getLogger(BiometricoController.class);
	
	@Autowired
	BiometricoSrv biometricoService;
	
	@Autowired
	EmpleadoSrv empleadoService;
	
	@GetMapping("/{numero}")
	public ResponseEntity<BiometricoResponse> buscarPorNumeroEmpleado(@PathVariable("numero") String numero) {
		ResponseEntity<BiometricoResponse> response = null;
		Biometrico biometrico = null;
		BiometricoResponse biometricoResponse = null;
		
		try {
			log.info("Consultado biometrico por número de empleado: {}", numero);
			biometrico = biometricoService.buscarPorNumeroEmpleado(numero);
			biometricoResponse = new BiometricoResponse();
			
			biometricoResponse.setNumero(biometrico.getEmpleado().getNumeroEmpleado());
			biometricoResponse.setBiometrico1(biometrico.getHuella1());
			biometricoResponse.setBiometrico2(biometrico.getHuella2());
			biometricoResponse.setCodigoError(0);
			biometricoResponse.setMensajeError("Respuesta correcta.");
			
			response = new ResponseEntity<BiometricoResponse>(biometricoResponse, HttpStatus.OK);
		} catch(Exception ex) {
			log.error("Problema para obtener el biometrico del empleado " + numero + "...", ex);
			biometricoResponse = new BiometricoResponse();
			biometricoResponse.setNumero(numero);
			biometricoResponse.setCodigoError(1);
			biometricoResponse.setMensajeError("La informacion solicitada es incorrecta.");
			response = new ResponseEntity<BiometricoResponse>(biometricoResponse, HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	@GetMapping("/{numero}/fotografia")
	public ResponseEntity<FotografiaResponse> buscarFotoPorNumeroEmpleado(@PathVariable("numero") String numero) {
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
