package com.ferbo.sgp.api.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.ferbo.sgp.api.model.Token;
import com.ferbo.sgp.api.model.response.BiometricoResponse;
import com.ferbo.sgp.api.model.response.EmpleadoResponse;
import com.ferbo.sgp.api.model.response.FotografiaResponse;
import com.ferbo.sgp.api.service.EmpleadoSrv;
import com.ferbo.sgp.api.service.TokenSrv;

@RestController
@RequestMapping("gestion")
@PreAuthorize("hasRole('SYSTEM')")
public class GestionController {
	private static Logger log = LogManager.getLogger(GestionController.class);
	
	@Autowired
	EmpleadoSrv empleadoService;
	
	@Autowired
	TokenSrv tokenService = null;
	
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
	
	@GetMapping("/{numero}/biometrico")
	public ResponseEntity<BiometricoResponse> buscarBiometrico(@PathVariable String numero) {
		ResponseEntity<BiometricoResponse> response = null;
		BiometricoResponse biometricoResponse = null;
		Empleado empleado = null;
		Token token = null;
		
		try {
			log.info("Consultando biometrico por número de empleado: {}", numero);
			empleado = empleadoService.buscarPorNumeroEmpleado(numero);
			
			token = tokenService.generaToken(empleado);
			
			biometricoResponse = new BiometricoResponse();
			biometricoResponse.setNumero(empleado.getNumeroEmpleado());
			biometricoResponse.setBiometrico1(empleado.getBiometrico().getHuella1());
			biometricoResponse.setBiometrico2(empleado.getBiometrico().getHuella2());
			biometricoResponse.setToken(token.getToken());
			biometricoResponse.setCodigoError(0);
			biometricoResponse.setMensajeError("Respuesta correcta.");
			
			response = new ResponseEntity<>(biometricoResponse, HttpStatus.OK);
		} catch(Exception ex) {
			log.error("Problema para obtener el biometrico del empleado " + numero + "...", ex);
			
			biometricoResponse = new BiometricoResponse();
			biometricoResponse.setNumero(numero);
			biometricoResponse.setCodigoError(1);
			biometricoResponse.setMensajeError("La información solicitada es incorrecta");
			
			response = new ResponseEntity<BiometricoResponse>(biometricoResponse, HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	@GetMapping("empleado")
	public ResponseEntity<List<EmpleadoResponse>> buscarEmpleado() {
		ResponseEntity<List<EmpleadoResponse>> response = null;
		List<EmpleadoResponse> listaResponse = null;
		EmpleadoResponse empleadoResponse = null;
		
		List<Empleado> empleados = null;
		
		try {
			log.info("Consultando información de los empleados");
			empleados = empleadoService.obtenerEmpleados();
			
			listaResponse = new ArrayList<>();
			for(Empleado e : empleados) {
				empleadoResponse = new EmpleadoResponse();
				empleadoResponse.setNumeroEmpleado(e.getNumeroEmpleado());
				empleadoResponse.setPrimerApellido(e.getPrimeroAp());
				empleadoResponse.setSegundoApellido(e.getSegundoAp());
				empleadoResponse.setNombre(e.getNombre());
				
				listaResponse.add(empleadoResponse);
			}
			
			response = new ResponseEntity<List<EmpleadoResponse>>(listaResponse, HttpStatus.OK);
			
		} catch(Exception ex) {
			log.error("Problema para obtener la información de los empleados...", ex);
			response = new ResponseEntity<List<EmpleadoResponse>>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
}
