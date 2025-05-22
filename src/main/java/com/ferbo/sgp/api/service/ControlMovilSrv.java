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

        Map<String, String> tokenResponse = new HashMap<String, String>();

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
        controlMovilRepo.save(nuevoToken);
        log.info("Finaliza proceso de fuardado de  token");

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

}
