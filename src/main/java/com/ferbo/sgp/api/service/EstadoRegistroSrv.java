package com.ferbo.sgp.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.EstadoRegistroDTO;
import com.ferbo.sgp.api.dto.EstatusAsistenciaDTO;
import com.ferbo.sgp.api.mapper.EstadoRegistroMapper;
import com.ferbo.sgp.api.mapper.EstatusAsistenciaMapper;
import com.ferbo.sgp.api.model.EstadoRegistro;
import com.ferbo.sgp.api.repository.EstadoRegistroRepo;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoRegistroSrv {
	@Autowired
	private EstadoRegistroRepo estadoRegistroRepo;

	@Autowired
	private EstadoRegistroMapper estadoRegistroMapper;
        
        @Autowired
        private EstatusAsistenciaMapper estatusAsistenciaMapper;

	@Query("SELECT e FROM EstadoRegistro e WHERE e.codiog = :codigo")
	public Optional<EstadoRegistro> buscarPorCodigo(String codigo) {
		return estadoRegistroRepo.findByCodigo(codigo);
	}
        
        public EstadoRegistroDTO obtenerPorCodigo(String codigo) {
		EstadoRegistro estadoRegistro = buscarPorCodigo(codigo)
				.orElseThrow(() -> new RuntimeException("No existe registro con el codigo " + codigo));

		return convertir(estadoRegistro);
	}
        
        public List<EstatusAsistenciaDTO> obtenerEstatusAsistencia() {
            return estadoRegistroRepo.findAll().stream().map(this::convertirEstatusAsistencia).collect(Collectors.toList());
        }

	EstadoRegistroDTO convertir(EstadoRegistro estadoRegistro) {
		return estadoRegistroMapper.toDTO(estadoRegistro);
	}
        
        public EstatusAsistenciaDTO convertirEstatusAsistencia(EstadoRegistro estadoRegistro) {
            return estatusAsistenciaMapper.toDTO(estadoRegistro);
        }
}
