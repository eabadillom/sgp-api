package com.ferbo.sgp.api.controller;

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

import com.ferbo.sgp.api.dto.ControlMovilDTO;
import com.ferbo.sgp.api.dto.SistemaDTO;
import com.ferbo.sgp.api.dto.UsuarioMovilDTO;
import com.ferbo.sgp.api.mapper.ControlMovilMapper;
import com.ferbo.sgp.api.model.ControlMovil;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.service.ControlMovilSrv;
import com.ferbo.sgp.api.service.SistemaSrv;
import com.ferbo.sgp.api.tool.ErrorResponseBuilder;
import com.ferbo.sgp.api.tool.SecurityTool;
import com.ferbo.tools.exception.RuleException;
import com.ferbo.tools.exception.SystemException;
import com.ferbo.tools.exception.ToolException;
import com.ferbo.tools.exception.ValidationException;

import static com.ferbo.sgp.api.tool.ErrorResponseBuilder.construirErrorMovil;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("movil")
public class MovilController {

    private static Logger log = LogManager.getLogger(MovilController.class);

    private static final String TIPO_ERROR_ACCESO = "Acceso";

    @Autowired
    SistemaSrv sistemaService;

    @Autowired
    private ControlMovilSrv controlMovilSrv;

    @Autowired
    private ControlMovilMapper controlMovilMapper;

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

    @PostMapping(value = "/cambiarPalabra", produces = "application/json")
    public ResponseEntity<?> cambiarPalabra(HttpServletRequest request, @RequestBody SistemaDTO usuario) {
        try {
            log.info("Incia proceso para extraer el token de las solicitud");
            String token = securityTool.extractBearerToken(request);
            log.info("Finaliza proceso para extraer el token de las solicitud");
            log.info("Inicia el proceso para obtener el control movil");
            ControlMovil controlMovil = controlMovilSrv. obtenerPorTokenSolicitante(token);
            log.info("Finaliza el proceso para obtener el control movil");
            log.info("Inicia proceso para cambiar la palabra secreta del sistema");
            Sistema usuarioActualiazado = sistemaService.actualizarContrasenia(controlMovil, usuario);
            log.info("Finaliza proceso para cambiar la palabra secreta del sistema");
            log.info("Inicia proceso para deshabilitar el ultimo token vigente del usuario");
            ControlMovil controlMovilDeshabilitado = controlMovilSrv.desahabilitarTokenPorControlMovil(controlMovil);
            controlMovil.setSistema(usuarioActualiazado);
            log.info("Finaliza proceso para deshabilitar el ultimo token vigente del usuario");
            ControlMovilDTO controlMovilDTO = controlMovilMapper.toDto(controlMovilDeshabilitado);
            controlMovilDTO.setId(null);
            return ResponseEntity.ok(controlMovilDTO);
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
