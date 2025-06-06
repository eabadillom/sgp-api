package com.ferbo.sgp.api.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.IncidenciaDTO;
import com.ferbo.sgp.api.dto.IncidenciaPermisoDTO;
import com.ferbo.sgp.api.mapper.IncidenciaMapper;
import com.ferbo.sgp.api.mapper.IncidenciaPermisoMapper;
import com.ferbo.sgp.api.model.DiaNoLaboral;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.EstadoRegistro;
import com.ferbo.sgp.api.model.EstatusSolicitud;
import com.ferbo.sgp.api.model.Incidencia;
import com.ferbo.sgp.api.model.InformacionEmpresa;
import com.ferbo.sgp.api.model.RegistroAsistencia;
import com.ferbo.sgp.api.model.SolicitudPermiso;
import com.ferbo.sgp.api.repository.DiaNoLaboralRepo;
import com.ferbo.sgp.api.repository.EmpleadoRepo;
import com.ferbo.sgp.api.repository.EstadoRegistroRepo;
import com.ferbo.sgp.api.repository.EstatusIncidenciaRepo;
import com.ferbo.sgp.api.repository.EstatusSolicitudRepo;
import com.ferbo.sgp.api.repository.IncidenciaRepo;
import com.ferbo.sgp.api.repository.RegistroAsistenciaRepo;
import com.ferbo.sgp.api.repository.SolicitudPermisoRepo;
import com.ferbo.sgp.api.tool.DateUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class IncidenciaSrv {
    
    private static final Logger log = LogManager.getLogger(IncidenciaSrv.class);

    @Autowired
    IncidenciaRepo incidenciaRepo;

    @Autowired
    IncidenciaMapper incidenciaMapper;
    
    @Autowired
    IncidenciaPermisoMapper incidenciaPermisoMapper;
    
    @Autowired
    EstatusIncidenciaRepo estatusIncidenciaRepo;
    
    @Autowired
    private SolicitudPermisoRepo solicitudPermisoRepo;
    
    @Autowired
    private EstatusSolicitudRepo estatusSoliciturRepo;
    
    @Autowired
    private EmpleadoRepo empleadoRepo;
    
    @Autowired
    private RegistroAsistenciaRepo asistenciaRepo;
    
    @Autowired
    private EstadoRegistroRepo estadoRegistroRepo;
    
    @Autowired
    private DiaNoLaboralRepo diaNoLaboralRepo;
    
    public IncidenciaPermisoDTO obtenerIncidenciaPorID(Integer id)
    {
        Incidencia incidencia = incidenciaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro registro de incidencia"));
        
        IncidenciaPermisoDTO incidenciaPermisoDTO = this.convertirPermiso(incidencia);
        
        return incidenciaPermisoDTO;
    }

    public List<IncidenciaDTO> obtenerIncidenciaTipoEnPeriodo(String claveTipo,
            String fechaInicial, String fechaFinal) throws RuntimeException {

        OffsetDateTime fechaInicio = DateUtil.stringToOffSetTime(fechaInicial);
        OffsetDateTime fechaFin = DateUtil.stringToOffSetTime(fechaFinal);

        fechaInicio = DateUtil.resetOffSetTime(fechaInicio);
        fechaFin = DateUtil.resetOffSetTime(fechaFin);

        if (fechaInicio.isAfter(fechaFin)) {
            OffsetDateTime fechaAux = fechaFin;
            fechaFin = fechaInicio;
            fechaInicio = fechaAux;
        }

        List<IncidenciaDTO> incidenciasDTO = incidenciaRepo
                .findByTipoEnPeriodo(claveTipo, fechaInicio, fechaFin).stream()
                .map(this::convertir).collect(Collectors.toList());

        if (incidenciasDTO.isEmpty()) {
            throw new RuntimeException("No existen registros");
        }

        return incidenciasDTO;
    }

    public IncidenciaPermisoDTO actualizarEstatusIncidencia(Integer id, IncidenciaPermisoDTO body) {
        
        log.info("Iniciando el actualizado de incidencia");
        Empleado empleadoRevision = empleadoRepo.findByNumeroEmpleado(body.getEmpleadoRev())
            .orElseThrow(() -> new RuntimeException("No se encontro registro de empleado con ese indentificador"));
        
        Incidencia incidencia = incidenciaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe incidencia con ese identificador"));
        
        SolicitudPermiso solicitudPermiso = solicitudPermisoRepo.findById(incidencia.getSolicitudPermiso().getIdSolicitud())
                .orElseThrow(() -> new RuntimeException("No se encontro registro de solicitud permiso"));
        
        BigDecimal valor = null;
        String sGoceSueldo = "100.0"; 
        if(solicitudPermiso.getEmpleadoSol().getEmpleadoConfiguracion().getGoceSueldo() == false)
        {
            valor = BigDecimal.ZERO;
        }else{
            if (sGoceSueldo != null && !sGoceSueldo.isEmpty()) 
            {
                valor = new BigDecimal(sGoceSueldo);
            }
        }
        
        if(body.getCodigoEstado().matches("R"))
        {
            solicitudPermiso.setDescripcionRechazo(body.getDescripcionRechazo());
        }
        solicitudPermiso.setGoceSueldo(valor);
        solicitudPermiso.setFechaMod(OffsetDateTime.now());
        solicitudPermiso.setEmpleadoRev(empleadoRevision);
        solicitudPermiso.setEstatusSolicitud(estatusSoliciturRepo.buscarPorClave(body.getCodigoEstado())
                .orElseThrow(() -> new RuntimeException("No se encontro registro de estatus solicitud")));
        solicitudPermisoRepo.save(solicitudPermiso);

        incidencia.setFechaModificacion(OffsetDateTime.now());
        incidencia.setEmpladoRevisa(empleadoRevision);
        incidencia.setEstatus(estatusIncidenciaRepo.findByClave(body.getCodigoEstado()).orElseThrow(
                () -> new RuntimeException("No existe estus con esa clave: " + body.getCodigoEstado())));
        
        if(incidencia.getEstatus().getClave().trim().matches("A"))
        {
            List<OffsetDateTime> diasAsueto = this.diasDeAsueto();
            this.guardarRegistroVacaciones(incidencia.getEmpleadoSol(), incidencia, diasAsueto);
        }

        incidenciaRepo.save(incidencia);
        log.info("Se actualizo correctamente la incidencia");
        return incidenciaPermisoMapper.toDTO(incidencia);
    }

    public IncidenciaDTO convertir(Incidencia incidencia) {
        return incidenciaMapper.toDTO(incidencia);
    }
    
    public IncidenciaPermisoDTO convertirPermiso(Incidencia incidencia){
        return incidenciaPermisoMapper.toDTO(incidencia);
    }
    
    public void guardarRegistroVacaciones(Empleado empleado, Incidencia incidencia, List<OffsetDateTime> diasAsueto)
    {
        this.empleadoTieneDiasLaborales(empleado);
        
        int cantidadRegistrosGuardados = 0;
        InformacionEmpresa empleadoEmpresa = empleado.getInformacionEmpresa();
        EstadoRegistro estadoRegistro = null;
        
        switch(incidencia.getSolicitudPermiso().getTipoSolicitud().getClave())
        {
            case "V":
                estadoRegistro = this.estatusVacaciones();
                break;
            case "P":
                estadoRegistro = this.estatusPermiso();
                break;
        }
        
        Integer horaEntrada = DateUtil.getHora(empleadoEmpresa.getHoraEntrada());
        Integer horaSalida = DateUtil.getHora(empleadoEmpresa.getHorasalida());
        
        OffsetDateTime fechaInicio = incidencia.getSolicitudPermiso().getFechaInicio();
        OffsetDateTime fechaFin = incidencia.getSolicitudPermiso().getFechaFin();
        
        List<OffsetDateTime> listaFechas = DateUtil.generarArreglosFechas(fechaInicio, fechaFin);
        
        listaFechas = DateUtil.diasVacacionesSolicitados(listaFechas, diasAsueto, empleado.getInformacionEmpresa());
        
        for(OffsetDateTime dia : listaFechas) 
        {
            RegistroAsistencia registro = new RegistroAsistencia();
            registro.setEmpleado(empleado);
            registro.setStatus(estadoRegistro);
            
            Date entrada = DateUtil.offsetDateTimeToDate(dia);

            Date registroEntrada = DateUtil.getDateTime(DateUtil.getAnio(entrada), DateUtil.getMes(entrada), DateUtil.getDia(entrada), horaEntrada, 0, 0, 0);
            log.trace("Dia hora entrada: {}", registroEntrada);
            registro.setFechaEntrada(DateUtil.dateToOffsetDateTime(registroEntrada));

            Date registroSalida = DateUtil.getDateTime(DateUtil.getAnio(entrada), DateUtil.getMes(entrada), DateUtil.getDia(entrada), horaSalida, 0, 0, 0);
            log.trace("Dia hora salida: {}", registroSalida);
            registro.setFechaSalida(DateUtil.dateToOffsetDateTime(registroSalida));

            asistenciaRepo.save(registro);
            cantidadRegistrosGuardados += 1;
        }
        
        log.info("Num. registros de incapacidad guardados del empleado {} en asistencia: {}", empleado.getIdEmpleado(), cantidadRegistrosGuardados);
    }
    
    public void empleadoTieneDiasLaborales(Empleado empleado)
    {
        if (empleado == null) {
            throw new RuntimeException("Error: El empleado no tiene informacion");
        }

        if (empleado.getInformacionEmpresa() == null) {
            throw new RuntimeException("Erro: El empleado no tiene informacion empresarial");
        }

        if (empleado.getInformacionEmpresa().getDiaLunes() == false || empleado.getInformacionEmpresa().getDiaMartes() == false || empleado.getInformacionEmpresa().getDiaMiercoles() == false || empleado.getInformacionEmpresa().getDiaJueves() == false || empleado.getInformacionEmpresa().getDiaViernes() == false) {
            throw new RuntimeException("Error: No tiene dias laborales asignados. Por favor contactar a RH");
        }

    }
    
    public EstadoRegistro estatusPermiso()
    {
        String permiso = "P";
        Optional<EstadoRegistro> estatusPermiso = estadoRegistroRepo.findByCodigo(permiso);
        return estatusPermiso.get();
    }
    
    public EstadoRegistro estatusVacaciones()
    {
        String vacaciones = "V";
        Optional<EstadoRegistro> estatusVacaciones = estadoRegistroRepo.findByCodigo(vacaciones);
        return estatusVacaciones.get();
    }
    
    public List<OffsetDateTime> diasDeAsueto() 
    {
        Integer anioEnCurso = DateUtil.getAnio(new Date());
        OffsetDateTime fechaInicio = DateUtil.inicializaFechaInicioAnioCurso(anioEnCurso - 1);
        OffsetDateTime fechaFin = DateUtil.inicializaFechaTerminoAnioCurso(anioEnCurso + 1);
        
        List<DiaNoLaboral> diasNoLaboral = diaNoLaboralRepo.buscarPorPeriodo("MX", fechaInicio, fechaFin);
        List<OffsetDateTime> diasDeAsueto = new ArrayList();
        
        for(DiaNoLaboral aux : diasNoLaboral) 
        {
            diasDeAsueto.add(aux.getFecha());
        }
        
        return diasDeAsueto;
    }
    
}
