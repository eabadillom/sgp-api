package com.ferbo.sgp.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.dto.ControlMovilDTO;
import com.ferbo.sgp.api.dto.SistemaDTO;
import com.ferbo.sgp.api.dto.UsuarioMovilDTO;
import com.ferbo.sgp.api.model.ControlMovil;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.service.EmpleadoSrv;
import com.ferbo.sgp.api.service.MovilResponseSrv;
import com.ferbo.sgp.api.service.SistemaSrv;
import com.ferbo.sgp.api.tool.ErrorResponseBuilder;
import com.ferbo.sgp.api.tool.SecurityTool;
import com.ferbo.tools.exception.RuleException;
import com.ferbo.tools.exception.SystemException;
import com.ferbo.tools.exception.ToolException;
import com.ferbo.tools.exception.ValidationException;

/**
 * Controller encargado de gestionar el acceso de dispositivos móviles que no
 * están asociados directamente a los servicios de negocio del sistema.
 * <p>
 * Esta clase se crea con la finalidad de separar el acceso de aquellos
 * dispositivos móviles que únicamente requieren validar su existencia en el
 * sistema, sin necesidad de utilizar directamente los servicios de negocio.
 * </p>
 * <p>
 * Actualmente existe un controller orientado específicamente a la gestión de
 * dispositivos móviles asociados al sistema. Este controller permite atender de
 * forma independiente a otros dispositivos móviles que requieren una validación
 * básica, evitando acoplarlos directamente con la lógica de negocio existente.
 * </p>
 */
@RestController
@RequestMapping("movil")
@PreAuthorize("hasRole('SYSTEM')")
public class DispositivoMovilController {

    private final static Logger log = LogManager.getLogger("GestionMovilValidationController");

    @Autowired 
    private SecurityTool securityTool;

    @Autowired 
    private SistemaSrv sistemaSrv;

    @Autowired 
    private MovilResponseSrv movilResponseSrv;

    @Autowired 
    private EmpleadoSrv empleadoSrv;

    /**
     * Función que se encarga de validar las credenciales del dispositivo movil solicitante
     * @param mobileAuthorization
     * @param usuario
     * @return
     */
    @PostMapping("/dispositivos/verificaciones")
    public ResponseEntity<?> validateMobile(
            @RequestHeader("Dispositivo-Mobile-Authorization")
            String mobileAuthorization, @RequestBody UsuarioMovilDTO usuario) {

        try {
            log.info("Inicia proceso para extraer las credenciales del dispositivo movil");
            String[] credenciales = securityTool.extractCredentials(mobileAuthorization);
            log.info("Finaliza proceso para extraer las credenciales del dispositivo movil");

            log.info("Inicia proceso para buscar al sistema celular asociado con las credenciales");
            Sistema sistema = sistemaSrv.buscarPorCredenciales(credenciales, Boolean.TRUE);
            log.info("Finaliza proceso para buscar al sistema celular asociado con las credenciales");

            log.info("Inicia proceso para buscar al empleado asignado al dispositivo movil");
            Empleado empleado = empleadoSrv.buscarPorNumeroEmpleado(usuario.getNumeroUsuario());
            log.info("Finaliza proceso para buscar al empleado asignado al dispositivo movil");

            log.info("Inicia proceso para contruir de forma parcial al usuario validado");
            UsuarioMovilDTO usuarioMovilValidado = movilResponseSrv.construirUsuarioMovilPorUsuarioYSistema(sistema, empleado);
            log.info("Finaliza proceso para contruir de forma parcial al usuario validado");

            log.info("Devolviendo al usuario movil validado");
            return ResponseEntity.ok(usuarioMovilValidado);

        } catch (ToolException ex) {
            log.info("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNAUTHORIZED, "Herramienta auxiliar", ex);
        } catch (ValidationException ex) {
            log.info("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNPROCESSABLE_ENTITY, "Validación", ex);
        } catch (SystemException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.NOT_FOUND, "Servicio externo", ex);
        } catch (Exception ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno", ex);
        }
        
    }
    
    @PostMapping(value = "/dispositivos/cambiarPassword", produces = "application/json")
    public ResponseEntity<?> cambiarContrasenia(@RequestHeader("Dispositivo-Mobile-Authorization")
            String mobileAuthorization) {
        try {

            log.info("Inicia proceso para extraer las credenciales del dispositivo movil");
            String[] credenciales = securityTool.extractCredentials(mobileAuthorization);
            log.info("Finaliza proceso para extraer las credenciales del dispositivo movil");

            log.info("Inicia proceso para buscar al sistema celular asociado con las credenciales");
            Sistema sistemaExistente = sistemaSrv.buscarPorCredenciales(credenciales, Boolean.FALSE);
            log.info("Finaliza proceso para buscar al sistema celular asociado con las credenciales");

            log.info("Inicia proceso para validar la nueva contraseña");
            sistemaSrv.validarContrasenia(credenciales[1]);
            log.info("Finaliza proceso para validar la nueva contraseña");

            log.info("Inicia proceso para cambiar la palabra secreta del sistema");
            sistemaSrv.actualizarContrasenia(sistemaExistente, credenciales[1]);
            log.info("Finaliza proceso para cambiar la palabra secreta del sistema");

            log.info("Inicia proceso para construir respuesta de proceso exitoso");
            ControlMovilDTO controlMovilDeshabilitado = new ControlMovilDTO();
            controlMovilDeshabilitado.setValido(Boolean.FALSE);
            log.info("Finaliza proceso para construir respuesta de proceso exitoso");

            return ResponseEntity.ok(controlMovilDeshabilitado);
        } catch (ToolException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, "Herramienta auxiliar" , ex);
        } catch(RuleException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNPROCESSABLE_ENTITY, "Regla de negocio" , ex);
        } catch (ValidationException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNPROCESSABLE_ENTITY, "Validación", ex);
        } catch (SystemException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.NOT_FOUND, "Servicio externo", ex);
        } catch (Exception ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno", ex);
        }
    }

}
