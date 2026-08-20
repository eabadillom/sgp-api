package com.ferbo.sgp.api.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.SistemaDTO;
import com.ferbo.sgp.api.mapper.SistemaMapper;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.repository.SistemaRepo;

@Service
public class SistemaSrv {
	private static Logger log = LogManager.getLogger(SistemaSrv.class);
	
	@Autowired
	private SistemaRepo sistemaRepo;
        
        @Autowired
        private SistemaMapper sistemaMapper;
	
	public Sistema buscarPorNombre(String nombreSistema) {
		Sistema sistema = null;
		log.info("Buscando sistema por nombre: {}", nombreSistema);
		sistema = sistemaRepo.findByNombre(nombreSistema);
		return sistema;
	}
        
        public SistemaDTO buscarDtoPorNombre(String nombreSistema) {
            Sistema sistema = sistemaRepo.findByNombre(nombreSistema);
            SistemaDTO sistemaDTO = this.convertir(sistema);
            return sistemaDTO;
        }
        
        public SistemaDTO convertir(Sistema sistema) {
            return sistemaMapper.toDTO(sistema);
        }
        
}
