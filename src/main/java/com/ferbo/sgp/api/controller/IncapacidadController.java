package com.ferbo.sgp.api.controller;

import com.ferbo.sgp.api.dto.EmpleadoIncDTO;
import com.ferbo.sgp.api.dto.IncapacidadDTO;
import com.ferbo.sgp.api.dto.IncapacidadDetalleDTO;
import com.ferbo.sgp.api.dto.TipoIncapacidadDTO;
import com.ferbo.sgp.api.model.ControlIncapacidad;
import com.ferbo.sgp.api.model.RiesgoTrabajo;
import com.ferbo.sgp.api.model.TipoRiesgo;
import com.ferbo.sgp.api.service.IncapacidadSrv;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alberto
 */
@RestController
@RequestMapping("movil")
public class IncapacidadController 
{
    private static Logger log = LogManager.getLogger(IncapacidadController.class);
    
    @Autowired
    private IncapacidadSrv incapacidadSrv;
    
    @GetMapping(value = "/incapacidades/{fechaIni}/{fechaFin}", produces = "application/json")
    public ResponseEntity<?> obtenerIncapacidadesPorPeriodo(@PathVariable String fechaIni, @PathVariable String fechaFin) 
    {
        List<IncapacidadDTO> incapacidadesDTO = null;
        
        try {
            log.info("Inicio proceso para obtener todas los incapacidades en base a los parametros dados.");
            incapacidadesDTO = incapacidadSrv.obtenerIncapacidadPorPeriodo(fechaIni, fechaFin);
            log.info("Finaliza proceso para obtener todas los incapacidades en base a los parametros dados.");
        } catch (RuntimeException rtEx) {
            log.warn("Problema al obtener las incapacidades en base a los parametros dados. {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener las incapacidades. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(incapacidadesDTO);
    }
    
    @GetMapping(value = "/incapacidad/{id}", produces = "application/json")
    public ResponseEntity<?> obtenerIncapacidadPorID(@PathVariable Integer id) 
    {
        IncapacidadDetalleDTO incapacidadDetalleDTO = null;
        
        try {
            log.info("Inicio proceso para obtener la incapacidad en base a los parametros dados.");
            incapacidadDetalleDTO = incapacidadSrv.obtenerIncapacidadPorId(id);
            log.info("Finaliza proceso para obtener la incapacidad en base a los parametros dados.");
        } catch (RuntimeException rtEx) {
            log.warn("Problema al obtener la incapacidad en base a los parametros dados. {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener la incapacidad. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(incapacidadDetalleDTO);
    }
    
    @GetMapping(value = "/incapacidad/empleados", produces = "application/json")
    public ResponseEntity<?> obtenerEmpleados() 
    {
        List<EmpleadoIncDTO> empleadosDTO = null;
        
        try {
            log.info("Inicio proceso para obtener todos los empleados.");
            empleadosDTO = incapacidadSrv.obtenerEmpleados();
            log.info("Finaliza proceso para obtener todos los empleados.");
        } catch (RuntimeException rtEx) {
            log.warn("Problema al obtener los empleados. {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener los empleados. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(empleadosDTO);
    }
    
    @GetMapping(value = "/incapacidad/tiposIncapacidades", produces = "application/json")
    public ResponseEntity<?> obtenerTiposIncapacidades() 
    {
        List<TipoIncapacidadDTO> tipoIncapacidadesDTO = null;
        
        try {
            log.info("Inicio proceso para obtener todos los tipos de incapacidades.");
            tipoIncapacidadesDTO = incapacidadSrv.obtenerTipoIncapacidad();
            log.info("Finaliza proceso para obtener todas los tipos de incapacidades.");
        } catch (RuntimeException rtEx) {
            log.warn("Problema al obtener los tipos de incapacidades. {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener los tipos de incapacidades. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(tipoIncapacidadesDTO);
    }
    
    @GetMapping(value = "/incapacidad/controlIncapacidades", produces = "application/json")
    public ResponseEntity<?> obtenerControlIncapacidades() 
    {
        List<ControlIncapacidad> controlIncapacidades = null;
        
        try {
            log.info("Inicio proceso para obtener el listado de control de incapacidades.");
            controlIncapacidades = incapacidadSrv.obtenerControlIncapacidad();
            log.info("Finaliza proceso para obtener el listado de control de incapacidades.");
        } catch (RuntimeException rtEx) {
            log.warn("Problema al obtener el listado de control de incapacidades. {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener el listado de control de incapacidades. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(controlIncapacidades);
    }
    
    @GetMapping(value = "/incapacidad/riesgosTrabajos", produces = "application/json")
    public ResponseEntity<?> obtenerRiesgosDeTrabajo() 
    {
        List<RiesgoTrabajo> riesgosDeTrabajo = null;
        
        try {
            log.info("Inicio proceso para obtener el listado de riesgo de trabajo.");
            riesgosDeTrabajo = incapacidadSrv.obtenerRiesgoTrabajo();
            log.info("Finaliza proceso para obtener el listado de riesgo de trabajo.");
        } catch (RuntimeException rtEx) {
            log.warn("Problema al obtener el listado de riesgo de trabajo. {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener el listado de riesgo de trabajo. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(riesgosDeTrabajo);
    }
    
    @GetMapping(value = "/incapacidad/tiposRiesgos", produces = "application/json")
    public ResponseEntity<?> obtenerTiposDeRiesgo() 
    {
        List<TipoRiesgo> tiposDeRiesgo = null;
        
        try {
            log.info("Inicio proceso para obtener el listado de tipos de riesgo.");
            tiposDeRiesgo = incapacidadSrv.obtenerTipoRiesgo();
            log.info("Finaliza proceso para obtener el listado de tipos de riesgo.");
        } catch (RuntimeException rtEx) {
            log.warn("Problema al obtener el listado de tipos de riesgo. {}", rtEx);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rtEx.getMessage());
        } catch (Exception ex) {
            log.error("Problema desconocido al obtener el listado de tipos de riesgo. {}", ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contacte con el administrador de sistemas");
        }
        return ResponseEntity.ok(tiposDeRiesgo);
    }
    
}
