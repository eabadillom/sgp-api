package com.ferbo.sgp.api.service;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.RegistroDetalleDTO;
import com.ferbo.sgp.api.dto.RegistroDTO;
import com.ferbo.sgp.api.mapper.RegistroDetalleMapper;
import com.ferbo.sgp.api.mapper.RegistroMapper;
import com.ferbo.sgp.api.model.RegistroAsistencia;
import com.ferbo.sgp.api.repository.EstadoRegistroRepo;
import com.ferbo.sgp.api.repository.RegistroAsistenciaRepo;
import com.ferbo.sgp.api.tool.DateUtil;

@Service
public class RegistroAsistenciaSrv {
	private static Logger log = LogManager.getLogger(RegistroAsistenciaSrv.class);

	@Autowired
	private RegistroAsistenciaRepo asistenciaRepo;

	@Autowired
	RegistroDetalleMapper registroCompletoMapper;

	@Autowired
	RegistroMapper registroParcialMapper;

	@Autowired
	EstadoRegistroRepo estadoRegistroRepo;

	public RegistroAsistencia guardar(RegistroAsistencia asistencia) {
		RegistroAsistencia asistenciaPrevia = null;
		OffsetDateTime fechaEntrada = asistencia.getFechaEntrada();

		OffsetDateTime fechaInicio = fechaEntrada.withHour(0).withMinute(0).withSecond(0).withNano(0);
		OffsetDateTime fechaFin = fechaEntrada.withHour(23).withMinute(59).withSecond(59).withNano(999);

		/*
		 * Se debe verificar en el SGP si el número de empleado ya cuenta con un
		 * registro de asistencia.
		 */
		Optional<RegistroAsistencia> oAsistencia = asistenciaRepo
				.buscarPorPeriodo(asistencia.getEmpleado().getNumeroEmpleado(), fechaInicio, fechaFin);

		if (oAsistencia.isPresent()) {
			/*
			 * Si hay un regitro previo de asistencia para el mismo día, entonces
			 * el usuario ya había registrado su asistencia.
			 */
			asistenciaPrevia = oAsistencia.get();

			if (asistencia.getFechaSalida() == null) {
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
			 */
			asistencia = asistenciaRepo.save(asistencia);
			log.info("Guardando asistencia: {}", asistencia);
		}

		return asistencia;
	}
	
	public List<RegistroDTO> obtenerPorPeriodoYEstatus(String fechaIni, String fechaFin, String codgio) {

		OffsetDateTime fechaInicio = DateUtil.stringToOffSetTime(fechaIni);
		OffsetDateTime fechaFinal = DateUtil.stringToOffSetTime(fechaFin);

		fechaInicio = DateUtil.resetOffSetTime(fechaInicio);
		fechaFinal = DateUtil.setToEndOfDay(fechaFinal);

		if (fechaInicio.isAfter(fechaFinal)) {
			OffsetDateTime fechaAux = fechaFinal;
			fechaFinal = fechaInicio;
			fechaInicio = fechaAux;
		}

		List<RegistroDTO> registrosAsistenciaDTO = asistenciaRepo
				.buscarPorPeriodoYEstatus(codgio, fechaInicio, fechaFinal)
				.stream()
				.map(this::convertir)
				.collect(Collectors.toList());

		return registrosAsistenciaDTO;
	}

	public RegistroDetalleDTO obtenerRegistoPorId(Integer id) throws Exception {

		RegistroAsistencia registro = asistenciaRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("No existe registro con ese identificador"));

		RegistroDetalleDTO registroCompletoDTO = registroCompletoMapper.toDTO(registro);

		return registroCompletoDTO;

	}

	public RegistroDetalleDTO actualizarEstatusRegistro(Integer id, RegistroDetalleDTO body) {

		RegistroAsistencia registro = asistenciaRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("No existe registro con ese identificador"));

		registro.setStatus(estadoRegistroRepo.findByCodigo(body.getCodigoRegistro())
				.orElseThrow(() -> new RuntimeException("No existe registro con el codigo " + body.getCodigoRegistro()) ));

		asistenciaRepo.save(registro);

		return registroCompletoMapper.toDTO(registro);

	}
        
        public RegistroAsistencia buscarPorFechaEstatus(Integer idEmp, OffsetDateTime fecha, String codigo)
        {
            RegistroAsistencia registro = asistenciaRepo.buscarPorFechaEstatus(idEmp, fecha, codigo)
                                .orElseThrow(() -> new RuntimeException("No existe registro con ese identificador y fechas"));
            return registro;
        }
        
        public List<RegistroAsistencia> buscarPorEmpPeriodoSolicitud (Integer idEmp, String codigo, OffsetDateTime fechaInicial, OffsetDateTime fechaFin)
        {
            List<RegistroAsistencia> listRegistroAsistencia = asistenciaRepo.buscarPorPeriodoSolicitud(idEmp, codigo, fechaInicial, fechaFin);
            return listRegistroAsistencia;
        }

	public RegistroDTO convertir(RegistroAsistencia registroAsistencia) {
		return registroParcialMapper.toDTO(registroAsistencia);
	}
}