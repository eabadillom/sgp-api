package com.ferbo.sgp.api.controller;

import com.ferbo.sgp.api.dto.RegistroCompletoDTO;
import com.ferbo.sgp.api.dto.RegistroParcialDTO;
import com.ferbo.sgp.api.model.EstadoRegistro;
import com.ferbo.sgp.api.model.RegistroAsistencia;
import com.ferbo.sgp.api.model.response.RegistroAsistenciaCompletoResponse;
import com.ferbo.sgp.api.model.response.RegistroAsistenciaParcialResponse;
import com.ferbo.sgp.api.service.EstadoRegistroSrv;
import com.ferbo.sgp.api.service.RegistroAsistenciaSrv;
import com.ferbo.sgp.api.tool.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author alberto
 */
@RestController
@RequestMapping("movil")
public class RegistroController 
{
    private static Logger log = LogManager.getLogger(RegistroController.class);
    
    @Autowired
    private RegistroAsistenciaSrv registroService;
    
    @Autowired
    private EstadoRegistroSrv estatusService;
    
    @GetMapping("/registros/{fechaInicio}/{codigo}")
    public ResponseEntity<List<RegistroAsistenciaParcialResponse>> obtenerDatos(@PathVariable String fechaInicio, @PathVariable String codigo) 
    {
        ResponseEntity<List<RegistroAsistenciaParcialResponse>> response = null;
        List<RegistroAsistenciaParcialResponse> listRegistroAsistenciaResponse = null;
        
        try
        {
            listRegistroAsistenciaResponse = new ArrayList();
            OffsetDateTime fechaIni = DateUtil.obtenerFechaString(fechaInicio);
            fechaIni = DateUtil.resetTime(fechaIni);

            List<RegistroAsistencia> lstRegistros = registroService.obtenerRegistrosPorFechasEstatus(fechaIni, codigo);
            List<RegistroParcialDTO> registrosParciales = registroService.convertParcialList(lstRegistros);
            
            listRegistroAsistenciaResponse = registrosParciales.stream()
                .map(RegistroAsistenciaParcialResponse::from)
                .collect(Collectors.toList());
            
            response = new ResponseEntity<List<RegistroAsistenciaParcialResponse>>(listRegistroAsistenciaResponse, HttpStatus.OK);
            log.info("Se obtuvo el listado de registros correctamente");
        }catch(Exception ex)
        {
            response = new ResponseEntity<List<RegistroAsistenciaParcialResponse>>(HttpStatus.NOT_FOUND);
            log.info("Error al obtener el listado de registros");
        }
        
        return response;
    }
    
    @GetMapping("/registros/{idRegistro}/estatus")
    public ResponseEntity<RegistroAsistenciaCompletoResponse> obtenerRegistro(@PathVariable Integer idRegistro) 
    {
        ResponseEntity<RegistroAsistenciaCompletoResponse> response = null;
        RegistroAsistenciaCompletoResponse registroAsistenciaResponse = null;
        
        try
        {
            RegistroAsistencia registro = registroService.obtenerRegistro(idRegistro);
            RegistroCompletoDTO registroCompleto = registroService.getRegistroCompletoDTO(registro);
            
            registroAsistenciaResponse = new RegistroAsistenciaCompletoResponse();
            registroAsistenciaResponse.setId(registroCompleto.getId());
            registroAsistenciaResponse.setFechaEntrada(registroCompleto.getFechaEntrada());
            registroAsistenciaResponse.setCodigo(registroCompleto.getCodigo());
            registroAsistenciaResponse.setNombre(registroCompleto.getNombre());
            registroAsistenciaResponse.setPrimeroAp(registroCompleto.getPrimeroAp());
            registroAsistenciaResponse.setSegundoAp(registroCompleto.getSegundoAp());
            registroAsistenciaResponse.setPlanta(registroCompleto.getPlanta());
            //registroAsistenciaResponse.setCodigoError(0);
            //registroAsistenciaResponse.setMensajeError("Respuesta correcta.");
            
            response = new ResponseEntity<RegistroAsistenciaCompletoResponse>(registroAsistenciaResponse, HttpStatus.OK);
            log.info("Se obtuvo el registro correctamente");
        }catch(Exception ex)
        {
            log.error("Error al obtener el registro " + idRegistro + "..." + ex);
            
            registroAsistenciaResponse = new RegistroAsistenciaCompletoResponse();
            registroAsistenciaResponse.setId(idRegistro);
            registroAsistenciaResponse.setCodigoError(1);
            registroAsistenciaResponse.setMensajeError("La información solicitada es incorrecta.");
            
            response = new ResponseEntity<RegistroAsistenciaCompletoResponse>(registroAsistenciaResponse, HttpStatus.NOT_FOUND);
        }
        
        return response;
    }
    
    @PostMapping("/registros/modificarRegistro")
    public ResponseEntity<RegistroAsistenciaCompletoResponse> actualizarRegistro(@RequestBody RegistroCompletoDTO registro) 
    {
        ResponseEntity<RegistroAsistenciaCompletoResponse> response = null;
        RegistroAsistenciaCompletoResponse registroAsistenciaResponse = null;
        
        try{
            EstadoRegistro estatusRegistroNuevo = estatusService.obtenerEstatusRegistro(registro.getCodigo());
            RegistroAsistencia registroCompleto = registroService.obtenerRegistro(registro.getId());
            
            registroCompleto.setStatus(estatusRegistroNuevo);
            registroService.actualizarRegistro(registroCompleto);
            
            registroAsistenciaResponse = new RegistroAsistenciaCompletoResponse();
            //registroAsistenciaResponse.setId(registroCompleto.getId());
            //registroAsistenciaResponse.setFechaEntrada(registroCompleto.getFechaEntrada());
            //registroAsistenciaResponse.setCodigo(registroCompleto.getStatus().getCodigo());
            //registroAsistenciaResponse.setNombre(registroCompleto.getEmpleado().getNombre());
            //registroAsistenciaResponse.setPrimeroAp(registroCompleto.getEmpleado().getPrimeroAp());
            //registroAsistenciaResponse.setSegundoAp(registroCompleto.getEmpleado().getSegundoAp());
            //registroAsistenciaResponse.setPlanta(registroCompleto.getEmpleado().getInformacionEmpresa().getPlanta().getDescripcion());
            registroAsistenciaResponse.setCodigoError(0);
            registroAsistenciaResponse.setMensajeError("Registro actualizado.");
            
            response = new ResponseEntity<RegistroAsistenciaCompletoResponse>(registroAsistenciaResponse, HttpStatus.OK);
            log.info("Se actualizo correctamente el registro");
        }catch(Exception ex)
        {
            registroAsistenciaResponse = new RegistroAsistenciaCompletoResponse();
            registroAsistenciaResponse.setId(registro.getId());
            registroAsistenciaResponse.setCodigoError(1);
            registroAsistenciaResponse.setMensajeError("Problema para guardar la informacion de asistencia.");
            
            response = new ResponseEntity<RegistroAsistenciaCompletoResponse>(registroAsistenciaResponse, HttpStatus.NOT_FOUND);
            log.error("Error al actualizar el registro");
        }
        
        return response;
    }
    
    @PatchMapping("/registros/modificarRegistro/{idRegistro}")
    public ResponseEntity<RegistroAsistenciaCompletoResponse> actualizarRegistro(@PathVariable Integer idRegistro) 
    {
        ResponseEntity<RegistroAsistenciaCompletoResponse> response = null;
        RegistroAsistenciaCompletoResponse registroAsistenciaResponse = null;
        String codigoEstatus = "J";
        
        try
        {
            EstadoRegistro estatusRegistroNuevo = estatusService.obtenerEstatusRegistro(codigoEstatus);
            RegistroAsistencia registroCompleto = registroService.obtenerRegistro(idRegistro);
            
            registroCompleto.setStatus(estatusRegistroNuevo);
            registroService.actualizarRegistro(registroCompleto);
            
            registroAsistenciaResponse = new RegistroAsistenciaCompletoResponse();
            //registroAsistenciaResponse.setId(registroCompleto.getId());
            //registroAsistenciaResponse.setFechaEntrada(registroCompleto.getFechaEntrada());
            //registroAsistenciaResponse.setCodigo(registroCompleto.getStatus().getCodigo());
            //registroAsistenciaResponse.setNombre(registroCompleto.getEmpleado().getNombre());
            //registroAsistenciaResponse.setPrimeroAp(registroCompleto.getEmpleado().getPrimeroAp());
            //registroAsistenciaResponse.setSegundoAp(registroCompleto.getEmpleado().getSegundoAp());
            //registroAsistenciaResponse.setPlanta(registroCompleto.getEmpleado().getInformacionEmpresa().getPlanta().getDescripcion());
            registroAsistenciaResponse.setCodigoError(0);
            registroAsistenciaResponse.setMensajeError("Registro actualizado.");
            
            response = new ResponseEntity<RegistroAsistenciaCompletoResponse>(registroAsistenciaResponse, HttpStatus.OK);
            log.info("Se actualizo correctamente el registro");
        }catch(Exception ex)
        {
            registroAsistenciaResponse = new RegistroAsistenciaCompletoResponse();
            registroAsistenciaResponse.setId(idRegistro);
            registroAsistenciaResponse.setCodigoError(1);
            registroAsistenciaResponse.setMensajeError("Problema para guardar la informacion de asistencia.");
            
            response = new ResponseEntity<RegistroAsistenciaCompletoResponse>(registroAsistenciaResponse, HttpStatus.NOT_FOUND);
            log.error("Error al actualizar el registro: " + idRegistro + "...." + ex);
        }
        
        return response;
    }
    
}
