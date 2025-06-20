package com.ferbo.sgp.api.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ferbo.sgp.api.dto.EmpleadoIncDTO;
import com.ferbo.sgp.api.dto.IncapacidadDTO;
import com.ferbo.sgp.api.dto.IncapacidadDetalleDTO;
import com.ferbo.sgp.api.dto.IncapacidadGuardarDetalleDTO;
import com.ferbo.sgp.api.dto.TipoIncapacidadDTO;
import com.ferbo.sgp.api.mapper.EmpleadoIncMapper;
import com.ferbo.sgp.api.mapper.IncapacidadDetalleMapper;
import com.ferbo.sgp.api.mapper.IncapacidadGuardarDetalleMapper;
import com.ferbo.sgp.api.mapper.IncapacidadMapper;
import com.ferbo.sgp.api.mapper.TipoIncapacidadMapper;
import com.ferbo.sgp.api.model.ControlIncapacidad;
import com.ferbo.sgp.api.model.DiaNoLaboral;
import com.ferbo.sgp.api.model.Empleado;
import com.ferbo.sgp.api.model.EstadoRegistro;
import com.ferbo.sgp.api.model.EstatusIncapacidad;
import com.ferbo.sgp.api.model.Incapacidad;
import com.ferbo.sgp.api.model.InformacionEmpresa;
import com.ferbo.sgp.api.model.RegistroAsistencia;
import com.ferbo.sgp.api.model.RiesgoTrabajo;
import com.ferbo.sgp.api.model.SolicitudPermiso;
import com.ferbo.sgp.api.model.TipoIncapacidad;
import com.ferbo.sgp.api.model.TipoRiesgo;
import com.ferbo.sgp.api.repository.ControlIncapacidadRepo;
import com.ferbo.sgp.api.repository.DiaNoLaboralRepo;
import com.ferbo.sgp.api.repository.EmpleadoRepo;
import com.ferbo.sgp.api.repository.EstadoRegistroRepo;
import com.ferbo.sgp.api.repository.EstatusIncapacidadRepo;
import com.ferbo.sgp.api.repository.IncapacidadRepo;
import com.ferbo.sgp.api.repository.RegistroAsistenciaRepo;
import com.ferbo.sgp.api.repository.RiesgoTrabajoRepo;
import com.ferbo.sgp.api.repository.SolicitudPermisoRepo;
import com.ferbo.sgp.api.repository.TipoIncapacidadRepo;
import com.ferbo.sgp.api.repository.TipoRiesgoRepo;
import com.ferbo.sgp.api.tool.DateUtil;
/**
 *
 * @author alberto
 */
@Service
public class IncapacidadSrv 
{
    private static final Logger log = LogManager.getLogger(IncapacidadSrv.class);
    
    @Autowired
    private IncapacidadRepo incapacidadRepo;
    
    @Autowired
    private EmpleadoRepo empleadoRepo;
    
    @Autowired
    private TipoIncapacidadRepo tipoIncapacidadRepo;
    
    @Autowired
    private ControlIncapacidadRepo controlIncapacidadRepo;
    
    @Autowired
    private RiesgoTrabajoRepo riesgoTrabajoRepo;
    
    @Autowired
    private TipoRiesgoRepo tipoRiesgoRepo;
    
    @Autowired
    private EstatusIncapacidadRepo estatusIncapacidadRepo;
    
    @Autowired
    private RegistroAsistenciaRepo asistenciaRepo;
    
    @Autowired
    private SolicitudPermisoRepo solicitudPermisoRepo;
    
    @Autowired
    private EstadoRegistroRepo estadoRegistroRepo;
    
    @Autowired
    private DiaNoLaboralRepo diaNoLaboralRepo;
    
    @Autowired
    private IncapacidadMapper incapacidadMapper;
    
    @Autowired
    private IncapacidadDetalleMapper incapacidadDetalleMapper;
    
    @Autowired
    private IncapacidadGuardarDetalleMapper incacidadGuardarDetalleMapper;
    
    @Autowired
    private EmpleadoIncMapper empleadoMapper;
    
    @Autowired
    private TipoIncapacidadMapper tipoIncapacidadMapper;
    
    public List<IncapacidadDTO> obtenerIncapacidadPorPeriodo(String fechaInicial, String fechaFinal) throws RuntimeException
    {
        OffsetDateTime fechaInicio = DateUtil.stringToOffSetTime(fechaInicial);
        OffsetDateTime fechaFin = DateUtil.stringToOffSetTime(fechaFinal);
        
        fechaInicio = DateUtil.resetOffSetTime(fechaInicio);
        fechaFin = DateUtil.resetOffSetTime(fechaFin);

        if (fechaInicio.isAfter(fechaFin)) {
            OffsetDateTime fechaAux = fechaFin;
            fechaFin = fechaInicio;
            fechaInicio = fechaAux;
        }
        
        List<IncapacidadDTO> incapacidadesDTO = incapacidadRepo.findByPeriodo(fechaInicio, fechaFin).stream()
                .map(this::convertir).collect(Collectors.toList());
        
        log.info("Num. de incapacidades: {}", incapacidadesDTO.size());
        /*if(incapacidadesDTO.isEmpty()){
            throw new RuntimeException("No hay registros de incapacidades");
        }*/
        
        return incapacidadesDTO;
    }
    
    public IncapacidadDetalleDTO obtenerIncapacidadPorId(Integer id)
    {
        Incapacidad incapacidad = incapacidadRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Error: no se encontro registro de incapacidad del empleado"));
        
        IncapacidadDetalleDTO incapacidadDetalleDTO = this.convertirDetalle(incapacidad);
        
        return incapacidadDetalleDTO;
    }
    
    public IncapacidadDetalleDTO cancelarIncapacidad(String numEmpleadoRev, IncapacidadDetalleDTO body)
    {
        String estatusIncapacidadCancelada = "C";
        EstatusIncapacidad estatusCancelada = estatusIncapacidadRepo.findByClave(estatusIncapacidadCancelada);
        
        Empleado empleadoRev = empleadoRepo.findByNumeroEmpleado(numEmpleadoRev)
            .orElseThrow(() -> new RuntimeException("Error: no se encontro registro de empleado revisión"));
        
        Incapacidad incapacidad = incapacidadRepo.findById(body.getIdIncapacidad())
            .orElseThrow(() -> new RuntimeException("Error: no se encontro registro de incapacidad del empleado"));
        
        incapacidad.setIdEmpleadoRev(empleadoRev);
        incapacidad.setEstatusSolicitud(estatusCancelada);
        
        String claveRegistro = "I";
        List<RegistroAsistencia> listaRegistroIncapacidades = obtenerRegistroAsistencia(incapacidad.getIdEmpleadoInc(), incapacidad.getFechaInicio(), incapacidad.getFechaFin(), claveRegistro);
        
        if(!listaRegistroIncapacidades.isEmpty())
        {
           int registrosBorrados = cancelarRegistroAsistencia(listaRegistroIncapacidades);
           log.info("Registros eliminados del empleado {} en asistencia: {}", incapacidad.getIdEmpleadoInc().getIdEmpleado(), registrosBorrados);
        }
        
        incapacidadRepo.save(incapacidad);
        
        IncapacidadDetalleDTO incapacidadDetalleDTO = this.convertirDetalle(incapacidad);
        
        return incapacidadDetalleDTO;
    }
    
    public List<EmpleadoIncDTO> obtenerEmpleados()
    {
        List<EmpleadoIncDTO> listEmpleadosDTO = empleadoRepo.findByActivo().stream()
                .map(this::convertirEmpleadoIncToDTO).collect(Collectors.toList());
        
        log.info("Num. de empleados: {}", listEmpleadosDTO.size());
        
        return listEmpleadosDTO;
    }
    
    public List<TipoIncapacidadDTO> obtenerTipoIncapacidad()
    {
        List<TipoIncapacidadDTO> listTiposIncapacidadesDTO = tipoIncapacidadRepo.findAll().stream()
                .map(this::convertirTipoIncapacidadToDTO).collect(Collectors.toList());
        
        log.info("Num. de tipos de incapacidades: {}", listTiposIncapacidadesDTO.size());
        
        return listTiposIncapacidadesDTO;
    }
    
    public List<ControlIncapacidad> obtenerControlIncapacidad()
    {
        List<ControlIncapacidad> listControlIncapacidad = controlIncapacidadRepo.findAll();
        
        log.info("Num. de control de incapacidades: {}", listControlIncapacidad.size());
        
        return listControlIncapacidad;
    }
    
    public List<RiesgoTrabajo> obtenerRiesgoTrabajo()
    {
        List<RiesgoTrabajo> listRiesgoTrabajo = riesgoTrabajoRepo.findAll();
        
        log.info("Num. de riesgos de trabajo: {}", listRiesgoTrabajo.size());
        
        return listRiesgoTrabajo;
    }
    
    public List<TipoRiesgo> obtenerTipoRiesgo()
    {
        List<TipoRiesgo> listTipoRiesgo = tipoRiesgoRepo.findAll();
        
        log.info("Num. de tipos de riesgos: {}", listTipoRiesgo.size());
        
        return listTipoRiesgo;
    }
    
    public IncapacidadGuardarDetalleDTO guardarIncapacidad(IncapacidadGuardarDetalleDTO body)
    {
        log.info("Iniciando el guardado de la incapacidad");
        Incapacidad incapacidad = null;
        try
        {
            incapacidad = new Incapacidad();
            boolean riesgoTrabajoDefuncion = false;
            List<OffsetDateTime> diasAsueto = this.diasDeAsueto();    

            Empleado empleadoInc = empleadoRepo.findById(body.getIdEmpleadoInc())
                    .orElseThrow(() -> new RuntimeException("Error: no existe empleado con ese identificador"));

            empleadoTieneDiasLaborales(empleadoInc);

            Empleado empleadpRev = empleadoRepo.findByNumeroEmpleado(body.getIdEmpleadoRev())
                .orElseThrow(() -> new RuntimeException("Error: no se encontro registro de empleado revisión"));

            InformacionEmpresa datoEmpresaEmpleado = empleadoInc.getInformacionEmpresa();
            if(datoEmpresaEmpleado == null)
            {
                throw new RuntimeException("Error: el empleado no tiene información empresarial, consulta con RH");
            }

            TipoIncapacidad tipoIncapacidad = tipoIncapacidadRepo.findById(body.getTipoIncapacidad())
                    .orElseThrow(() -> new RuntimeException("Error: no existe tipo incapacidad con ese identificador"));

            ControlIncapacidad controlIncapacidad = controlIncapacidadRepo.findById(body.getControlIncapacidad())
                    .orElseThrow(() -> new RuntimeException("Error: no existe control incapacidad con ese identificador"));

            OffsetDateTime fechaIni = body.getFechaInicio();
            OffsetDateTime fechaFin = null;
            if(body.getDiasAutorizados() == 0)
            {

                fechaFin = fechaIni;
            }else
            {
                fechaFin = DateUtil.dateToOffsetDateTime(DateUtil.agregaFechaFin(DateUtil.offsetDateTimeToDate(fechaIni), body.getDiasAutorizados()));
            }

            OffsetDateTime fechaCaptura = OffsetDateTime.now();
            EstatusIncapacidad estatusIncapacidadAceptada = estatusIncapacidadRepo.findByClave("A");

            incapacidad.setIdEmpleadoInc(empleadoInc);
            incapacidad.setIdEmpleadoRev(empleadpRev);
            incapacidad.setTipoIncapacidad(tipoIncapacidad);
            incapacidad.setControlIncapacidad(controlIncapacidad);

            RiesgoTrabajo riesgoTrabajo = null;
            TipoRiesgo tipoRiesgo = null;

            if(body.getRiesgoTrabajo() != null)
            {
                riesgoTrabajo = riesgoTrabajoRepo.findById(body.getRiesgoTrabajo())
                        .orElseThrow(() -> new RuntimeException("Error: no existe riesgo de trabajo con ese identificador"));
                incapacidad.setSecuelaRiesgoTrabajo(riesgoTrabajo);
            }

            if(body.getTipoRiesgo() != null)
            {
                tipoRiesgo = tipoRiesgoRepo.findById(body.getTipoRiesgo())
                        .orElseThrow(() -> new RuntimeException("Error: no existe tipo de riesgo con ese identificador"));
                incapacidad.setTipoRiesgo(tipoRiesgo);
            }

            incapacidad.setFolio(body.getFolio());
            incapacidad.setDiasAutorizados(body.getDiasAutorizados());
            incapacidad.setFechaInicio(fechaIni);
            incapacidad.setFechaFin(fechaFin);
            incapacidad.setFechaCaptura(fechaCaptura);
            incapacidad.setDescripcion(body.getDescripcion());
            incapacidad.setEstatusSolicitud(estatusIncapacidadAceptada);

            log.info("info incapacidad {}", incapacidad.toString());
            riesgoTrabajoDefuncion = validarSolicitudIncapacidad(incapacidad);
            
            incapacidadRepo.save(incapacidad);
            
            if(riesgoTrabajoDefuncion == false)
            {
                this.guardarRegistroIncapacidad(empleadoInc, incapacidad.getFechaInicio(), incapacidad.getFechaFin(), diasAsueto);
            }
            
        }catch (DataIntegrityViolationException ex) 
        {
            String mensaje = "Error: uno de los atributos de la incapacidad ya se encuentra repetido";
            throw new RuntimeException(mensaje);
        }
        
        IncapacidadGuardarDetalleDTO incapacidadDetalleDTO = this.convertirGuardarDetalle(incapacidad);
        return incapacidadDetalleDTO;
    }
    
    public void guardarRegistroIncapacidad(Empleado empleado, OffsetDateTime fechaInicio, OffsetDateTime fechaFin, List<OffsetDateTime> diasAsueto)
    {
        empleadoTieneDiasLaborales(empleado);
        
        int cantidadRegistrosGuardados = 0;
        InformacionEmpresa empleadoEmpresa = empleado.getInformacionEmpresa();
        EstadoRegistro estadoRegistro = this.estatusIncapacidad();
        
        Integer horaEntrada = DateUtil.getHora(empleadoEmpresa.getHoraEntrada());
        Integer horaSalida = DateUtil.getHora(empleadoEmpresa.getHorasalida());
        
        List<OffsetDateTime> listaFechas = DateUtil.generarArreglosFechas(fechaInicio, fechaFin);
        log.trace("Lista de Fechas: {}", listaFechas);
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
            throw new RuntimeException("Error: el empleado no tiene informacion");
        }

        if (empleado.getInformacionEmpresa() == null) {
            throw new RuntimeException("Error: el empleado no tiene informacion empresarial");
        }

        if (empleado.getInformacionEmpresa().getDiaLunes() == false || empleado.getInformacionEmpresa().getDiaMartes() == false || empleado.getInformacionEmpresa().getDiaMiercoles() == false || empleado.getInformacionEmpresa().getDiaJueves() == false || empleado.getInformacionEmpresa().getDiaViernes() == false) {
            throw new RuntimeException("Error: el empleado no tiene dias laborales asignados. Por favor contactar a RH");
        }
    }
    
    public EstadoRegistro estatusIncapacidad()
    {
        String vacaciones = "I";
        EstadoRegistro estatusVacaciones = estadoRegistroRepo.findByCodigo(vacaciones)
                .orElseThrow(() -> new RuntimeException("No se encontro registro de estatus incapacidad"));
        return estatusVacaciones;
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
    
    public boolean validarSolicitudIncapacidad(Incapacidad incapacidad)
    {
        boolean regDefuncion = false;
        ControlIncapacidad conIncapacidad = incapacidad.getControlIncapacidad();
        RiesgoTrabajo secuelaRiesgoTrabajo = incapacidad.getSecuelaRiesgoTrabajo();
        RiesgoTrabajo riesgoIncapacidadDefuncion = riesgoTrabajoRepo.findByCodigo("D");
        String descripcion = "";
        String clave = "";
        
        if(conIncapacidad != null)
        {
            descripcion = conIncapacidad.getDescripcion();
            clave = conIncapacidad.getClave();
        }
        
        if(secuelaRiesgoTrabajo != null)
        {
            descripcion = secuelaRiesgoTrabajo.getDescripcion();
            clave = secuelaRiesgoTrabajo.getClave();
        }
        
        if(clave.trim().matches("D"))
        {
            //En caso de incapacidad del tipo defuncion, se da de baja al empleado
            String descripcionDefuncion = riesgoIncapacidadDefuncion.getDescripcion();
            log.info("Descripcion del tipo baja defuncion: {}", descripcionDefuncion);
            modificarRegistroIncapacidad(incapacidad, descripcion);
            regDefuncion = true;
        }else
        {
            validarRegistroIncapacidad(incapacidad);
            
            validarPeriodoSolicitudPermiso(incapacidad);
            regDefuncion = false;
        }
        
        return regDefuncion;
    }
    
    public void modificarRegistroIncapacidad(Incapacidad incapacidad, String descripcion)
    {
        Empleado empleado = incapacidad.getIdEmpleadoInc();
        OffsetDateTime fechaInicial = incapacidad.getFechaInicio();
        
        log.info("Baja del empleado {} en la fecha {}", empleado.getIdEmpleado(), fechaInicial, descripcion);
        Incapacidad auxRegistroIncapacidad = incapacidadRepo.buscarPorEmpleadoUltimoPeriodo(empleado.getIdEmpleado(), fechaInicial);
        
        if(auxRegistroIncapacidad != null)
        {
            log.trace("Registro de incapacidad encontrado: {}", auxRegistroIncapacidad.toString());
            log.trace("Fecha original: {}", fechaInicial);
            OffsetDateTime fechaIni = DateUtil.moverFechaUnDiaAdelante(fechaInicial);
            log.trace("Fecha adelantada: {}", fechaIni);
            
            String clave = "I";
            List<RegistroAsistencia> listaRegistroIncapacidades = obtenerRegistroAsistencia(empleado, fechaIni, auxRegistroIncapacidad.getFechaFin(), clave);
            int numeroRegistrosIncapacidades = listaRegistroIncapacidades.size();

            if(numeroRegistrosIncapacidades > 0)
            {
                int registrosBorrados = cancelarRegistroAsistencia(listaRegistroIncapacidades);
                log.info("Registros eliminados de asistencia: {}", registrosBorrados);
            }
            
        }
        
        this.actualizarRegistroEmpleado(empleado, fechaInicial, descripcion);
    }
    
    public List<RegistroAsistencia> obtenerRegistroAsistencia(Empleado empleado, OffsetDateTime fechaInicio, OffsetDateTime fechaFin, String clave) throws RuntimeException
    {
        RegistroAsistencia registro = null;
        InformacionEmpresa empleadoEmpresa = empleado.getInformacionEmpresa();
        Integer horaEntrada = DateUtil.getHora(empleadoEmpresa.getHoraEntrada());
        Integer horaSalida = DateUtil.getHora(empleadoEmpresa.getHorasalida());
        log.info("Buscando registros del empleado {} en asistencia", empleado.getIdEmpleado());
        
        fechaInicio = DateUtil.setHourTime(fechaInicio, horaEntrada, 0, 0, 0);
        log.info("Fecha inicio: {}", fechaInicio);
        fechaFin = DateUtil.setHourTime(fechaFin, horaSalida, 0, 0, 0);
        log.info("Fecha fin: {}", fechaFin);
        
        List<OffsetDateTime> listaFechasAsistencia = DateUtil.generarArreglosFechas(fechaInicio, fechaFin);
        listaFechasAsistencia = DateUtil.diasVacacionesSolicitados(listaFechasAsistencia, diasDeAsueto(), empleado.getInformacionEmpresa());
        int totalFechasIncapacidades = listaFechasAsistencia.size();
        
        List<RegistroAsistencia> listaRegistroAsistencia = asistenciaRepo.buscarPorPeriodoSolicitud(empleado.getIdEmpleado(), clave, fechaInicio, fechaFin);
        int totalRegistros = listaRegistroAsistencia.size();
        horaEntrada = 12;
        
        log.trace("Tamaño rango fechas {}, Tamaño rango registros {}", totalFechasIncapacidades, totalRegistros);
        
        if(totalRegistros > 0)
        {
            listaRegistroAsistencia.clear();
            log.info("Lista Fechas Asistencias: {}", listaFechasAsistencia.toString());
            for(OffsetDateTime aux : listaFechasAsistencia)
            {
                OffsetDateTime auxFecha = DateUtil.getOffsetDateTime(aux.getYear(), aux.getMonth().getValue(), aux.getDayOfMonth(), horaEntrada, 0, 0);
                log.info("Aux Fecha: {}", auxFecha);
                registro = asistenciaRepo.buscarPorFechaEstatus(empleado.getIdEmpleado(), auxFecha, clave)
                    .orElseThrow(() -> new RuntimeException("Error: no existe registro con num. empleado y fechas"));
                if(registro != null)
                {
                    listaRegistroAsistencia.add(registro);
                }
            }
            
            log.info("El empleado {} tiene {} registros en asistencia", empleado.getIdEmpleado(), totalRegistros);
            
            if(listaRegistroAsistencia.isEmpty())
            {
                throw new RuntimeException("Error: no se encontraron registros en asistencia del empleado");
            }
        }else
        {
            log.info("El empleado {} tiene {} registros en asistencia", empleado.getIdEmpleado(), totalRegistros);
        }
        
        return listaRegistroAsistencia;
    }
    
    public int cancelarRegistroAsistencia(List<RegistroAsistencia> listaRegistros) throws RuntimeException
    {
        int totalRegistros = 0;
        
        try
        {
            for(RegistroAsistencia auxRegistro : listaRegistros)
            {
                asistenciaRepo.delete(auxRegistro);
                totalRegistros = totalRegistros + 1;
            }
        }catch(RuntimeException ex)
        {
            log.info("Error al borrar un registo de asistencia: {}", ex);
            throw new RuntimeException("Error: no se puede cancelar la incapacidad");
        }
        return totalRegistros;
    }
    
    public void actualizarRegistroEmpleado(Empleado empleado, OffsetDateTime fecha, String descripcion)
    {
        InformacionEmpresa empleadoEmpresa = empleado.getInformacionEmpresa();
        empleadoEmpresa.setFechaBaja(fecha.toLocalDate());
        empleadoEmpresa.setMotivobaja(descripcion);
        empleado.setInformacionEmpresa(empleadoEmpresa);
        empleado.setActivo(0);
        empleadoRepo.save(empleado);
    }
    
    public void validarRegistroIncapacidad(Incapacidad incapacidad)
    {
        log.info("Entrando a validar incapacidades");
        Empleado empleado = incapacidad.getIdEmpleadoInc();
        OffsetDateTime fechaInicio = incapacidad.getFechaInicio();
        OffsetDateTime fechaFinal = incapacidad.getFechaFin();
        String claveEstatus = "A";
        
        List<Incapacidad> auxRegistroIncapacidad = incapacidadRepo.findByParametros(empleado.getIdEmpleado(), fechaInicio, fechaFinal, claveEstatus);
        
        if(auxRegistroIncapacidad.isEmpty())
        {
            log.info("No se encontraron registros de incapacidades");
        }else
        {
            log.info("Incapacidades encontradas: {}", auxRegistroIncapacidad.toString());
            for(Incapacidad auxIncapacidad : auxRegistroIncapacidad)
            {
                if(auxIncapacidad.equals(incapacidad))
                {
                    throw new RuntimeException("Error: el empleado ya cuenta con un registro de incapacidad");
                }
                
                if(incapacidad.getFolio().trim().equals(auxIncapacidad.getFolio().trim()))
                {
                    throw new RuntimeException("Error: el folio se encuentra duplicado");
                }
                
                boolean auxRegDefuncion = this.validaRegistroEmpleadoDefuncion(auxIncapacidad);
                if(auxIncapacidad.getSecuelaRiesgoTrabajo() != null && auxIncapacidad.getSecuelaRiesgoTrabajo().getClave().trim().matches("D") && auxRegDefuncion == true)
                {
                    throw new RuntimeException("Error: el empleado ya tiene un registro de defunción");
                }
            }
            
            if(!auxRegistroIncapacidad.isEmpty())
            {
                log.trace("Registros de incapacidades del empleado {} encontradas: {}", empleado.getIdEmpleado(), auxRegistroIncapacidad.toString());
                throw new RuntimeException("Error: el empleado ya cuenta con un registro de incapacidad");
            }
        }
        
        List<Incapacidad> listIncapacidades = (List<Incapacidad>) incapacidadRepo.findAll();
        
        for(Incapacidad auxIncapacidad : listIncapacidades)
        {
            if(auxIncapacidad.getFolio().trim().equals(incapacidad.getFolio().trim()))
            {
                throw new RuntimeException("Error: el folio se encuentra duplicado");
            }
        }
    }
    
    public boolean validaRegistroEmpleadoDefuncion(Incapacidad auxIncapacidad)
    {
        boolean regDefuncion = false;
        Empleado empleado = auxIncapacidad.getIdEmpleadoInc();
        InformacionEmpresa empleadoEmpresa= empleado.getInformacionEmpresa();
        
        if(empleado.getActivo() == 0 && empleadoEmpresa.getFechaBaja() != null)
        {
            regDefuncion = true;
        }
        
        return regDefuncion;
    }
    
    public void validarPeriodoSolicitudPermiso(Incapacidad incapacidad) throws RuntimeException
    {
        log.info("Entrando a validar vacaciones y/o permisos");
        String enviada = "E";
        String aprobada = "A";
        Integer idEmpleadoInc = incapacidad.getIdEmpleadoInc().getIdEmpleado();
        OffsetDateTime fechaInicio = incapacidad.getFechaInicio();
        OffsetDateTime fechaFin = incapacidad.getFechaFin();
        log.trace("Fecha Inicial: {} y Fecha Final: {} del Empleado: {}", fechaInicio, fechaFin, idEmpleadoInc);
        
        List<SolicitudPermiso> lstSolicitudes = solicitudPermisoRepo.buscarPorEmpPeriodo(idEmpleadoInc, fechaInicio, fechaFin, enviada, aprobada);
        
        if(!lstSolicitudes.isEmpty())
        {
            log.trace("Los registros de vacaciones y/o permisos son: {}", lstSolicitudes.toString());
            throw new RuntimeException("Error: el empleado ya tiene un periodo de vacaciones y/o permiso");
        }
    }
    
    public IncapacidadDTO convertir(Incapacidad incapacidad) {
        return incapacidadMapper.toDTO(incapacidad);
    }
    
    public IncapacidadDetalleDTO convertirDetalle(Incapacidad incapacidad) {
        return incapacidadDetalleMapper.toDTO(incapacidad);
    }
    
    public IncapacidadGuardarDetalleDTO convertirGuardarDetalle(Incapacidad incapacidad)
    {
        return incacidadGuardarDetalleMapper.toDTO(incapacidad);
    }
    
    public EmpleadoIncDTO convertirEmpleadoIncToDTO(Empleado empleado)
    {
        return empleadoMapper.toDTO(empleado);
    }
    
    public TipoIncapacidadDTO convertirTipoIncapacidadToDTO(TipoIncapacidad tipoIncapacidad)
    {
        return tipoIncapacidadMapper.toDTO(tipoIncapacidad);
    }
    
}
