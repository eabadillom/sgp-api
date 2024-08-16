package com.ferbo.sgp.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.model.RegistroAsistencia;
import com.ferbo.sgp.api.repository.RegistroAsistenciaRepo;

@Service
public class RegistroAsistenciaSrv {
	private static Logger log = LogManager.getLogger(RegistroAsistenciaSrv.class);
	
	@Autowired
	private RegistroAsistenciaRepo asistenciaRepo;
	
	public RegistroAsistencia guardar(RegistroAsistencia asistencia) {
		RegistroAsistencia asistenciaPrevia = null;
		LocalDateTime fechaEntrada = asistencia.getFechaEntrada();
		
		LocalDateTime fechaInicio = LocalDateTime.of(fechaEntrada.getYear(), fechaEntrada.getMonth(), fechaEntrada.getDayOfMonth(), 0, 0, 0, 0);
		LocalDateTime fechaFin = LocalDateTime.of(fechaEntrada.getYear(), fechaEntrada.getMonth(), fechaEntrada.getDayOfMonth(), 23, 59, 59, 999);
		
		/*Se debe verificar en el SGP si el número de empleado ya cuenta con un registro de asistencia.*/
		Optional<RegistroAsistencia> oAsistencia = asistenciaRepo.buscarPorPeriodo(asistencia.getEmpleado().getNumeroEmpleado(), fechaInicio, fechaFin);
		
		if(oAsistencia.isPresent()) {
			/*  Si hay un regitro previo de asistencia para el mismo día, entonces
			 *  el usuario ya había registrado su asistencia.
			 */
			asistenciaPrevia = oAsistencia.get();
			
			
			if(asistencia.getFechaSalida() == null) {
				asistenciaPrevia.setFechaSalida(asistencia.getFechaEntrada());
			} else {
				asistenciaPrevia.setFechaSalida(asistencia.getFechaSalida());
			}
			asistencia = asistenciaRepo.save(asistenciaPrevia);
			log.info("Actualizando asistencia: {}", asistencia);
		} else {
			/**
			 * En caso de no tener registro de asistencia en SGP, entonces
			 * se guardará un nuevo registro tal como se recibe de la sincronización
			 * del FP-Client
			 * */
			asistencia = asistenciaRepo.save(asistencia);
			log.info("Guardando asistencia: {}", asistencia);
		}
		
		return asistencia;
	}
}