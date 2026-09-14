package com.ferbo.sgp.api.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.SistemaDTO;
import com.ferbo.sgp.api.mapper.SistemaMapper;
import com.ferbo.sgp.api.model.ControlMovil;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.repository.SistemaRepo;
import com.ferbo.sgp.api.tool.SecurityTool;
import com.ferbo.tools.exception.RuleException;
import com.ferbo.tools.exception.SystemException;
import com.ferbo.tools.exception.ValidationException;

@Service
public class SistemaSrv {
	private static Logger log = LogManager.getLogger(SistemaSrv.class);
	
	@Autowired
	private SistemaRepo sistemaRepo;
        
        @Autowired
        private SistemaMapper sistemaMapper;

        @Autowired
        private SecurityTool securityTool;
	
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

    
    public synchronized Sistema actualizarContrasenia(ControlMovil token, SistemaDTO usuario) throws Exception, SystemException, ValidationException {

        if (usuario == null) {
            throw new ValidationException("El usuario no puede ser vacío");
        }

        if (token == null) {
            throw new ValidationException("El token no pueden ser vacío");
        }

        if (token.getSistema() == null) {
            throw new ValidationException("El token no esta asociado a ningun usuario"); 
        }

        Integer idSistema = token.getSistema().getId();


        Sistema sistema = sistemaRepo.findById(idSistema).orElseThrow(() -> new SystemException("No hay ningun sistema registrado con ese usuario"));

        String palabra = usuario.getPassword();

        if (palabra == null || "".equalsIgnoreCase(palabra)) {
            throw new RuleException("La contraseña no puede ser vacía");
        }

        securityTool.checkPassword(palabra);

        String secreto = securityTool.cifrarBCrypt(palabra);

        sistema.setPassword(secreto);

        sistemaRepo.save(sistema);

        return sistema;
    }
        
}
