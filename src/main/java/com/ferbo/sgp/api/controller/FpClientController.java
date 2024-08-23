package com.ferbo.sgp.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.model.Biometrico;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.EstadoRegistro;
import com.ferbo.sgp.api.model.InformacionEmpresa;
import com.ferbo.sgp.api.model.RegistroAsistencia;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.model.Token;
import com.ferbo.sgp.api.model.request.BiometricoRequest;
import com.ferbo.sgp.api.model.response.BiometricoResponse;
import com.ferbo.sgp.api.service.BiometricoSrv;
import com.ferbo.sgp.api.service.EmpleadoSrv;
import com.ferbo.sgp.api.service.EstadoRegistroSrv;
import com.ferbo.sgp.api.service.InformacionEmpresaSrv;
import com.ferbo.sgp.api.service.RegistroAsistenciaSrv;
import com.ferbo.sgp.api.service.SistemaSrv;
import com.ferbo.sgp.api.service.TokenSrv;

@RestController
@RequestMapping("fp-client")
@PreAuthorize("hasRole('FP_CLIENT')")
public class FpClientController {
	private static Logger log = LogManager.getLogger(FpClientController.class);
	
	@Autowired
	BiometricoSrv biometricoService;
	
	@Autowired
	RegistroAsistenciaSrv asistenciaService;
	
	@Autowired
	EmpleadoSrv empleadoService;
	
	@Autowired
	EstadoRegistroSrv estadoService;
	
	@Autowired
	SistemaSrv sistemaService;
	
	@Autowired
	InformacionEmpresaSrv informacionEmpresaService;
	
	@Autowired
	TokenSrv tokenService = null;
	
	
	/* Obtiene la lista de empleados asignados a la planta donde se encuentra configurado el FP_CLIENT.
	 * */
	@GetMapping("/empleado")
	public ResponseEntity<List<BiometricoResponse>> obtenerPorFpClient() {
		ResponseEntity<List<BiometricoResponse>> response = null;
		List<BiometricoResponse> listaResponse = null;
		BiometricoResponse biometricoResponse = null;
		UserDetails userDetails = null;
		String fpClientName = null;
		Sistema sistema = null;
		List<InformacionEmpresa> listaInfoEmpresa = null;
		
		try {
			log.info("Buscando lista de biometricos...");
			
			userDetails = (UserDetails) SecurityContextHolder
					.getContext()
					.getAuthentication()
					.getPrincipal();
			fpClientName = userDetails.getUsername();
			
			log.info("Usuario solictante: {}", fpClientName);
			sistema = sistemaService.buscarPorNombre(fpClientName);
			
			listaInfoEmpresa = informacionEmpresaService.buscarPorIdPlanta(sistema.getPlanta().getId());
			
			listaResponse = new ArrayList<BiometricoResponse>();
			for(InformacionEmpresa i : listaInfoEmpresa) {
				biometricoResponse = new BiometricoResponse();
				if(i.getEmpleado() == null)
					continue;
				
				if(i.getEmpleado().getBiometrico() == null)
					continue;
				
				biometricoResponse.setNumero(i.getEmpleado().getNumeroEmpleado());
				biometricoResponse.setBiometrico1(i.getEmpleado().getBiometrico().getHuella1());
				biometricoResponse.setBiometrico2(i.getEmpleado().getBiometrico().getHuella2());
				
				listaResponse.add(biometricoResponse);
			}
			
			response = new ResponseEntity<List<BiometricoResponse>>(listaResponse, HttpStatus.OK);
		} catch(Exception ex) {
			log.error("Probolema para obtener el listado de biometricos...", ex);
			response = new ResponseEntity<List<BiometricoResponse>>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
	@GetMapping("/empleado/{numero}")
	public ResponseEntity<BiometricoResponse> obtenerPorNumeroEmpleado(@PathVariable("numero") String numero) {
		ResponseEntity<BiometricoResponse> response = null;
		Biometrico biometrico = null;
		
		BiometricoResponse biometricoResponse = null;
		Token token = null;
		
		try {
			log.info("Consultado biometrico por número de empleado: {}", numero);
			biometrico = biometricoService.buscarPorNumeroEmpleado(numero);
			
			biometricoResponse = new BiometricoResponse();
			token = tokenService.generaToken(biometrico.getEmpleado());
			biometricoResponse.setNumero(biometrico.getEmpleado().getNumeroEmpleado());
			biometricoResponse.setBiometrico1(biometrico.getHuella1());
			biometricoResponse.setBiometrico2(biometrico.getHuella1());
			biometricoResponse.setToken(token.getToken());
			biometricoResponse.setCodigoError(0);
			biometricoResponse.setMensajeError("Respuesta correcta.");
			
			response = new ResponseEntity<BiometricoResponse>(biometricoResponse, HttpStatus.OK);
			log.info("Respuesta: {}", biometricoResponse);
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
	
	@PostMapping("/empleado")
	public ResponseEntity<List<BiometricoResponse>> guardarRegistroAsistencia(@RequestBody List<BiometricoRequest> registroAsistencia) {
		ResponseEntity<List<BiometricoResponse>> response = null;
		List<BiometricoResponse> listaBiometricoResponse = null;
		BiometricoResponse biometricoResponse = null;
		EstadoRegistro estado = null;
		Empleado empleado = null;
		RegistroAsistencia registro = null;
		RegistroAsistencia registroSaved = null;
		
		try {
			log.info("Biometrico: {}", registroAsistencia);
			
			estado = estadoService.buscarPorCodigo("T").get();
			listaBiometricoResponse = new ArrayList<BiometricoResponse>();
			
			for(BiometricoRequest asistencia : registroAsistencia) {
				log.info("Asistencia recibida: {}", asistencia);
				empleado = empleadoService.buscarPorNumeroEmpleado(asistencia.getNumero());
				
				registro = new RegistroAsistencia();
				registro.setEmpleado(empleado);
				registro.setStatus(estado);
				registro.setFechaEntrada(asistencia.getHoraEntrada());
				registro.setFechaSalida(asistencia.getHoraSalida());
				
				registroSaved = asistenciaService.guardar(registro);
				
				biometricoResponse = new BiometricoResponse();
				biometricoResponse.setUuid(asistencia.getUuid());
				biometricoResponse.setNumero(asistencia.getNumero());
				biometricoResponse.setHoraEntrada(asistencia.getHoraEntrada());
				biometricoResponse.setHoraSalida(asistencia.getHoraSalida());
				biometricoResponse.setCodigoError(0);
				biometricoResponse.setMensajeError("Registro correcto.");
				
				log.info("Registro guardado: {}", biometricoResponse);
				listaBiometricoResponse.add(biometricoResponse);
			}
			log.info("Registro(s) procesado(s): {}", listaBiometricoResponse.size());
			
			response = new ResponseEntity<List<BiometricoResponse>>(listaBiometricoResponse, HttpStatus.ACCEPTED);
		} catch(Exception ex) {
			log.error("Problema al guardar la información de asistencia...",ex);
			biometricoResponse = new BiometricoResponse();
			biometricoResponse.setCodigoError(1);
			biometricoResponse.setMensajeError("Problema al guardar la información de asistencia.");
			response = new ResponseEntity<List<BiometricoResponse>>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
}
