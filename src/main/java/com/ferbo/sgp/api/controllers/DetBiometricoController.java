package com.ferbo.sgp.api.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.models.DetBiometricoModel;
import com.ferbo.sgp.api.services.DetBiometricoService;
import com.ferbo.sgp.api.services.DetEmpleadoService;

@RestController
@RequestMapping("/detBiometrico")
public class DetBiometricoController {
	@Autowired
	DetBiometricoService detBiometricoService;

	@Autowired
	DetEmpleadoService detEmpleadoService;

	@GetMapping()
	public ArrayList<DetBiometricoModel> obtenerBiometricos() {
		return detBiometricoService.obtenerBiometricos();
	}

	@GetMapping(path = "/{id}")
	public Optional<DetBiometricoModel> obtenerUsuariosporId(@PathVariable("id") Integer id) {
		return this.detBiometricoService.obtenerPorId(id);
	}

	@GetMapping("/biometrico")
	public DetBiometricoModel obtenerUsuarioPorNoEmpleado(@RequestParam("idEmpleado") Integer idEmpleado) {

		return detBiometricoService.obtenerNoEmpleado(idEmpleado);
	}

}
