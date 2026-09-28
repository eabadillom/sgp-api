package com.ferbo.sgp.api.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.SistemaDTO;
import com.ferbo.sgp.api.mapper.SistemaMapper;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.repository.SistemaRepo;
import com.ferbo.sgp.api.tool.SecurityTool;
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

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public Sistema buscarPorNombre(String nombreSistema) {
		Sistema sistema = null;
		log.info("Buscando sistema por nombre: {}", nombreSistema);
		sistema = sistemaRepo.findByNombre(nombreSistema);
		return sistema;
	}

    public Sistema buscarPorCredenciales(String[] sistemaBuscado, Boolean considerarContrasenia) {

        if (sistemaBuscado.length == 0){
            throw new ValidationException("El sistema a buscar no puede ser vacío");
        }

        String usuario = sistemaBuscado[0];

        if (usuario == null || "".equalsIgnoreCase(usuario)) {
            throw new ValidationException("El nombre del sistema a buscar no puede ser vacío");
        }

        Sistema sistema = sistemaRepo.findByNombre(usuario);

        if (sistema == null) {
            throw new SystemException("El sistema buscado no se encuentra registrado");
        }

        if (considerarContrasenia) {
            if (!passwordEncoder.matches(sistemaBuscado[1], sistema.getPassword())) {
                throw new ValidationException("La contraseña introducida no es valída.");
            }
        }


        return  sistema;

    }
        
    public SistemaDTO buscarDtoPorNombre(String nombreSistema) {
        Sistema sistema = sistemaRepo.findByNombre(nombreSistema);
        SistemaDTO sistemaDTO = this.convertir(sistema);
        return sistemaDTO;
    }
        
        public SistemaDTO convertir(Sistema sistema) {
            return sistemaMapper.toDTO(sistema);
        }

    
    public synchronized void actualizarContrasenia(Sistema sistema, String contrasenia) throws Exception, SystemException, ValidationException {

        if (sistema == null) {
            throw new ValidationException("El usuario no puede ser vacío");
        }

        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            throw new ValidationException("La nueva contraseña no puede estar vacía");
        }

        String secreto = securityTool.cifrarBCrypt(contrasenia);

        sistema.setPassword(secreto);

        sistemaRepo.save(sistema);
    }
 
    public void validarContraseniaDesdeDTO (SistemaDTO sistemaDTO) throws Exception {

        if (sistemaDTO == null) {
            throw new ValidationException("La contraseña no puede estar vacía");
        }

        validarContrasenia(sistemaDTO.getPassword());
        
    }

    public void validarContrasenia(String password) throws Exception {
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("La contraseña no puede ser vacía, ni se un espacio en blanco.");
        }

        securityTool.checkPassword(password.trim());
    }
}
