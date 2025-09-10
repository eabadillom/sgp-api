package com.ferbo.sgp.api.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.auth.JwtUtil;
import com.ferbo.sgp.api.dto.UsuarioMovilDTO;
import com.ferbo.sgp.api.model.ControlMovil;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.Sistema;
import com.ferbo.sgp.api.repository.ControlMovilRepo;
import com.ferbo.sgp.api.tool.DateUtil;
import com.ferbo.sgp.api.tool.SecurityTool;

@Service
public class ControlMovilSrv {

    private static Logger log = LogManager.getLogger(ControlMovilSrv.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    SistemaSrv sistemaService;

    @Autowired
    private SecurityTool securityTool;

    @Autowired
    private ControlMovilRepo controlMovilRepo;

    @Autowired
    private EmpleadoSrv empleadoService;

    public UsuarioMovilDTO obtenerUsuario(HttpServletRequest request, UsuarioMovilDTO body) {
        UsuarioMovilDTO usuario = new UsuarioMovilDTO();

        String[] credenciales = null;

        String token = null;
        String refreshToken = null;

        Boolean valido = Boolean.TRUE;

        Map<String, String> tokenResponse = new HashMap<String, String>();

        log.info("Inicia proceso de extraccion de credenciales");
        credenciales = securityTool.extractCredentials(request);
        token = jwtUtil.generateToken(credenciales[0]);
        refreshToken = jwtUtil.generateRefreshToken(credenciales[1]);
        log.info("Finaliza proceso de extraccion de credenciales");

        log.info("Inicia proceso de obtencion del sistema");
        Sistema sistemaReferencia = sistemaService.buscarPorNombre(credenciales[0]);
        log.info("Finaliza proceso de obtencion del sistema");

        Date hoy = DateUtil.now();
        DateUtil.resetTime(hoy);

        log.info("Inicia proceso para generar el token");
        tokenResponse.put("access_token", token);
        tokenResponse.put("refresh_token", refreshToken);
        log.info("Finaliza proceso para generar el token");

        Date fechaExpiracion = DateUtil.addDay(hoy, 7);

        ControlMovil nuevoToken = controlMovilRepo.findTopBySistemaOrderByIdDesc(sistemaReferencia);

        if(nuevoToken != null) { 
            int validezToken = hoy.compareTo(nuevoToken.getExpiracion());
            log.info("El ultimo token del sistema solicitante: {}", nuevoToken);
            if(validezToken <= 0 && nuevoToken.getValido()){
                log.info("Ya existe un token valido, se mantiene");
                log.info("Inicia proceso de para notificar del token existente");
                token = nuevoToken.getToken();
                refreshToken = nuevoToken.getToken();
                fechaExpiracion = nuevoToken.getExpiracion();
                valido = nuevoToken.getValido();
                log.info("Finaliza proceso de para notificar del token existente");
            } else {
                nuevoToken = new ControlMovil();
            }
        } else {
            nuevoToken = new ControlMovil(); 
        }

        nuevoToken.setSistema(sistemaReferencia);
        nuevoToken.setExpiracion(fechaExpiracion);
        nuevoToken.setToken(token);
        nuevoToken.setValido(valido);

        log.info("Inicia proceso de guardado de  token");
        controlMovilRepo.save(nuevoToken);
        log.info("Finaliza proceso de guardado de  token");

        log.info("Inicia proceso para construir el usurio");

        log.info("Inicia proceso para obtener el empleado por su numero");
        Empleado empleado = empleadoService.buscarPorNumeroEmpleado(body.getNumeroUsuario());
        log.info("Finaliza proceso para obtener el empleado por su numero");

        usuario.setNumeroUsuario(empleado.getNumeroEmpleado());
        usuario.setNombreUsuario(empleado.getNombre());
        usuario.setPrimerApUsuario(empleado.getPrimeroAp());
        usuario.setSegundoApUsuario(empleado.getSegundoAp());
        usuario.setToken(token);
        usuario.setRefreshToken(refreshToken);
        usuario.setPuesto(empleado.getInformacionEmpresa().getPuesto().getDescripcion());

        log.info("Finaliza proceso para construir el usurio");

        return usuario;
    }

    public String deshabilitarToken(String authHeader){
         String token = authHeader.replace("Bearer ", "");
        
		ControlMovil controlMovil = controlMovilRepo.findByToken(token).orElseThrow(()-> new RuntimeException("No se tiene un sistema asginado"));

        controlMovil.setValido(Boolean.FALSE);

        controlMovilRepo.save(controlMovil);

        return "El proceso finalizo exitosamente";
    }
}
