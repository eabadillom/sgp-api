package com.ferbo.sgp.api.controllers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.models.DetBiometricoModel;
import com.ferbo.sgp.api.models.DetEmpleadoModel;
import com.ferbo.sgp.api.services.DetBiometricoService;
import com.ferbo.sgp.api.services.DetEmpleadoService;

@RestController
@RequestMapping("/detEmpleado")
public class DetEmpleadoController {

	private static Logger log = LogManager.getLogger(DetEmpleadoController.class);

	@Autowired
	DetEmpleadoService detEmpleadoService;

	@Autowired
	DetBiometricoService detBiometricoService;

	@GetMapping()
	public ArrayList<DetEmpleadoModel> obtenerEmpleados() {
		return detEmpleadoService.obtenerEmpleados();
	}

	@GetMapping("/empleado")
	public DetEmpleadoModel obtenerPorNumEmpleado(@RequestParam("numEmpleado") String numEmpleado) {
		return detEmpleadoService.obtenerPorNumEmpleado(numEmpleado);
	}

	@CrossOrigin("*")
	@GetMapping("/empleadoBio")
	public DetBiometricoModel obtenerPorNumEmp(@RequestParam("numEmp") String numEmpleado) {

		log.info("Consultando biometricos");

		Integer idEmpleado = detEmpleadoService.obtenerPorNumEmpleado(numEmpleado).getIdEmpleado();

		Integer idEmpBio = detBiometricoService.obtenerNoEmpleado(idEmpleado).getIdEmpleado();

		if (idEmpBio == idEmpleado) {
			return detBiometricoService.obtenerNoEmpleado(idEmpleado);
		} else {
			return null;
		}

	}

}
