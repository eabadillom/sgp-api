package com.ferbo.sgp.api.controller;

import com.ferbo.sgp.api.dto.ControlMovilDTO;
import com.ferbo.sgp.api.dto.SistemaDTO;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.dto.UsuarioMovilDTO;
import com.ferbo.sgp.api.model.ControlMovil;
import com.ferbo.sgp.api.service.ControlMovilSrv;
import com.ferbo.sgp.api.service.SistemaSrv;
import com.ferbo.sgp.api.tool.ErrorResponseBuilder;
import com.ferbo.sgp.api.tool.SecurityTool;
import com.ferbo.tools.exception.RuleException;
import com.ferbo.tools.exception.SystemException;
import com.ferbo.tools.exception.ToolException;
import com.ferbo.tools.exception.ValidationException;

@RestController
@RequestMapping("movil")
public class MovilController {

    private static Logger log = LogManager.getLogger(MovilController.class);

    private static final String TIPO_ERROR_ACCESO = "Acceso";

    @Autowired
    private ControlMovilSrv controlMovilSrv;
    
    @Autowired 
    private SistemaSrv sistemaSrv;

    @Autowired
    private SecurityTool securityTool;

    @PostMapping("/generar")
    public ResponseEntity<?> inicioPantalla(HttpServletRequest request, @RequestBody UsuarioMovilDTO body) {
        UsuarioMovilDTO usuario = null; 
        try{
            log.info("Inicia el proceso para generar el usuario");
            usuario = controlMovilSrv.obtenerUsuario(request, body);
            log.info("Finaliza el proceso para generar el usuario");
        } catch(RuntimeException ex){
            log.warn("Hubo un problema al obtener los datos. {}", ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_ACCESO, ex);
        } catch(Exception ex){
            log.error("Problema desconocido. {}", ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_ACCESO, ex);
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/verificar")
    public ResponseEntity<?> verificarToken() {
        return ResponseEntity.ok("Acceso autorizado");
    }

    @GetMapping("/deshabilitar")
    public ResponseEntity<?> deshabilitarToken(@RequestHeader("Authorization") String authHeader){
        try {
        log.info("Inicia proceso para desahibilitar el token del sistema.");
        String respuesta = controlMovilSrv.deshabilitarToken(authHeader);
        log.info("Finaliza proceso para desahibilitar el token del sistema.");
        return ResponseEntity.ok(respuesta);
        
       } catch (RuntimeException ex) {
        log.warn("Hubo un problema al desahibilitar el token del sistema. {}", ex);
        return ErrorResponseBuilder.construirErrorMovil(HttpStatus.NOT_FOUND, TIPO_ERROR_ACCESO, ex);
       } catch (Exception ex) {
        log.error("Hubo un problema al desahibilitar el token del sistema. {}", ex);
        return ErrorResponseBuilder.construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, TIPO_ERROR_ACCESO, ex);
       }
    }
    
    @PostMapping("/autenticacion/cambiarPassword")
    public ResponseEntity<?> cambiarContrasenia(HttpServletRequest request, @RequestBody SistemaDTO body) {
        try {
            log.info("Inicia proceso para extraer el token de la solicitud");
            String token = securityTool.extractBearerToken(request);
            log.info("Finaliza proceso para extraer el token de la solicitud");
            
            log.info("Inicia proceso para obtener el sistema asociado al token");
            ControlMovil controlMovil = controlMovilSrv.obtenerPorTokenSolicitante(token);
            log.info("Finaliza proceso para obtener el sistema asociado al token");
            
            //faltan pasos intermedios
            log.info("Inicia proceso para validar la nueva contraseña");
            sistemaSrv.validarContrasenia(body.getPassword());
            log.info("Finaliza proceso para validar la nueva contraseña");
            
            log.info("Inicia proceso para cambiar la palabra secreta del sistema");
            sistemaSrv.actualizarContrasenia(controlMovil.getSistema(), body.getPassword());
            log.info("Finaliza proceso para cambiar la palabra secreta del sistema");
            
            log.info("Inicia proceso para deshabilitar el ultimo token registrado del dispositivo");
            ControlMovil controlMovilDeshabilitado = controlMovilSrv.desahabilitarTokenPorControlMovil(controlMovil);
            log.info("Finaliza proceso para deshabilitar el ultimo token registrado del dispositivo");
            
            log.info("Inicia proceso para contruir respuesta para el dispositivo movil");
            ControlMovilDTO respuesta = controlMovilSrv.convertirControlMovil(controlMovilDeshabilitado);
            log.info("Finaliza proceso para contruir respuesta para el dispositivo movil");

            log.info("Se establece que el token ya no sera valído");
            respuesta.setValido(Boolean.FALSE);

            log.info("Se envía respuesta al dispositivo movil");
            return ResponseEntity.ok(respuesta);
        }catch (ToolException ex) {
            log.info("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNAUTHORIZED, "Herramienta auxiliar", ex);
        } catch (ValidationException ex) {
            log.info("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNPROCESSABLE_ENTITY, "Validación", ex);
        } catch (SystemException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.NOT_FOUND, "Servicio externo", ex);
        } catch (RuleException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.CONFLICT, "Regla de negocio", ex);
        } catch (Exception ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado al procesar la solicitud. Intenta nuevamente más tarde.", ex);
        }
    }

}
