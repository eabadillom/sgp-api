package com.ferbo.sgp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferbo.sgp.api.auth.JwtUtil;
import com.ferbo.sgp.api.model.ControlMovil;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.service.ControlMovilSrv;
import com.ferbo.sgp.api.service.SistemaSrv;
import com.ferbo.sgp.api.tool.DateUtil;
import com.ferbo.sgp.api.tool.SecurityTool;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("movil")
@PreAuthorize("hasRole('FP_CLIENT')")
public class MovilController {

    private static Logger log = LogManager.getLogger(MovilController.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    SistemaSrv sistemaService;

    @Autowired
    private SecurityTool securityTool;

    @Autowired
    private ControlMovilSrv controlMovilSrv;

    @GetMapping("/inicio")
    public ResponseEntity<?> inicioPantalla(HttpServletRequest request) {

        String[] credenciales = null;

        String token = null;
        String refreshToken = null;

        Map<String, String> tokenResponse = new HashMap<String, String>();

        log.info("Incia el proceso para gererar, guardar y enviars un token");

        log.info("Inicia proceso de extraccion de credenciales");
        credenciales = securityTool.extractCredentials(request);
        token = jwtUtil.generateToken(credenciales[0]);
        refreshToken = jwtUtil.generateRefreshToken(credenciales[1]);
        log.info("Finaliza proceso de extraccion de credenciales");

        log.info("Inicia proceso para generar el token");
        tokenResponse.put("access_token", token);
        tokenResponse.put("refresh_token", refreshToken);
        log.info("Finaliza proceso para generar el token");

        log.info("Inicia proceso de generar el token");
        ControlMovil nuevoToken = new ControlMovil();
        Sistema sistemaReferencia = sistemaService.buscarPorNombre(credenciales[0]);

        nuevoToken.setSistema(sistemaReferencia);
        Date hoy = DateUtil.now();
        DateUtil.resetTime(hoy);
        Date fechaExpiracion = DateUtil.addDay(hoy, 7);
        nuevoToken.setExpiracion(fechaExpiracion);
        nuevoToken.setToken(token);
        log.info("Finaliza proceso de generar el token");

        log.info("Inicia proceso de fuardado de  token");
        controlMovilSrv.guardarToken(nuevoToken);
        log.info("Finaliza proceso de fuardado de  token");

        log.info("Finaliza el proceso para gererar, guardar y enviar token");
        return ResponseEntity.ok(tokenResponse);

        /*
         * try {
         * 
         * } catch (HTTPException ex) {
         * log.error("Hubo un problema al momento de hacer el proceso del token", ex);
         * return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
         * .body("Problema en el servicio. Contactar al administrador del sistema");
         * } catch (Exception e) {
         * log.error("Error inesperado: ", e);
         * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
         * .body("Error inesperado. Contactar al administrador");
         * }
         */
    }

}
