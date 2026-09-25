package com.ferbo.sgp.api.service;


import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.UsuarioMovilDTO;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.tools.exception.BusinessException;
import com.ferbo.tools.exception.ValidationException;

@Service 
public class MovilResponseSrv {

    /** Construye la respuesta de forma parcial, utilizando el campo destinado al token
        para almacenar temporalmente el perfil del empleado.
        Debido a que actualmente ya no se genera un token, se aprovecha este campo
        para incluir la información del perfil del empleado en la respuesta. De esta
        manera, el consumidor puede obtener toda la información requerida en una sola
        respuesta, evitando realizar una consulta adicional a la API únicamente para
        obtener el perfil del empleado.
        @param sistema Sistema desde el cual se realiza la solicitud.
        @param empleado Información del empleado.
        @return Respuesta construida parcialmente con la información para el control movil.
*/
    public UsuarioMovilDTO construirUsuarioMovilPorUsuarioYSistema(Sistema sistema, Empleado empleado) {

        if (sistema == null) {
            throw new ValidationException("El sistema no puede ser vacío");
        }

        if (sistema.getId() == null) {
            throw new BusinessException("El sistema debe estar registrado encuentra registrado");
        }

        if (empleado == null) {
            throw  new ValidationException("El empleado no puede ser vacío");
        }

        if (empleado.getIdEmpleado() == null) {
            throw new BusinessException("El empleado no se encuentra registrado en el sistema");
        }

        UsuarioMovilDTO usuarioMovilDTO = new UsuarioMovilDTO();

        usuarioMovilDTO.setNombreUsuario(empleado.getNombre());
        usuarioMovilDTO.setPrimerApUsuario(empleado.getPrimeroAp());
        usuarioMovilDTO.setSegundoApUsuario(empleado.getSegundoAp());
        usuarioMovilDTO.setNumeroUsuario(empleado.getNumeroEmpleado());
        usuarioMovilDTO.setPuesto(empleado.getInformacionEmpresa().getPerfil().getDescripcion());
        usuarioMovilDTO.setToken(String.valueOf(empleado.getInformacionEmpresa().getPerfil().getId()));

        
        return usuarioMovilDTO;
     }
}
