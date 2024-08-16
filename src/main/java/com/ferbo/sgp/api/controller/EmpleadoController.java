package com.ferbo.sgp.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.service.EmpleadoSrv;

@RestController
@RequestMapping("/empleado")
@PreAuthorize("hasRole('SYSTEM')")
public class EmpleadoController {

	private static Logger log = LogManager.getLogger(EmpleadoController.class);

	@Autowired
	EmpleadoSrv empleadoService;

	@GetMapping
	public Empleado obtenerPorNumEmpleado(@RequestParam("numero") String numEmpleado) {
		log.info("Consultando información del empleado: {}", numEmpleado);
		return empleadoService.buscarPorNumeroEmpleado(numEmpleado);
	}
}
