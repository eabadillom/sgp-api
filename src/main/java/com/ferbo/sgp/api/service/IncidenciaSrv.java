package com.ferbo.sgp.api.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.ferbo.sgp.api.model.RegistroVacaciones;
import com.ferbo.sgp.api.model.SolicitudPermiso;
import com.ferbo.sgp.api.repository.DiaNoLaboralRepo;
import com.ferbo.sgp.api.repository.EmpleadoRepo;
import com.ferbo.sgp.api.repository.EstadoRegistroRepo;
import com.ferbo.sgp.api.repository.EstatusIncidenciaRepo;
import com.ferbo.sgp.api.repository.EstatusSolicitudRepo;
import com.ferbo.sgp.api.repository.IncidenciaRepo;
import com.ferbo.sgp.api.repository.RegistroAsistenciaRepo;
import com.ferbo.sgp.api.repository.RegistroVacacionesRepo;
import com.ferbo.sgp.api.repository.SolicitudPermisoRepo;
import com.ferbo.sgp.api.tool.DateUtil;

@Service
public class IncidenciaSrv {

    private static final Logger log = LogManager.getLogger(IncidenciaSrv.class);

    private static final String TP_VACACIONES = "V";
    private static final String TP_FALTA = "F";
    public static final String TP_PERMISO = "P";
    public static final String TE_ACEPTADO = "A";
    public static final String TE_RECHAZADO = "R";

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

    @Autowired
    private RegistroVacacionesRepo registroVacacionesRepo;

    public IncidenciaPermisoDTO obtenerIncidenciaPorID(Integer id) {
        Incidencia incidencia = incidenciaRepo.buscarPorIdIncidenciaPermiso(id)
                .orElseThrow(() -> new RuntimeException("No se encontro registro de incidencia"));

        IncidenciaPermisoDTO incidenciaPermisoDTO = this.convertirPermiso(incidencia);

        return incidenciaPermisoDTO;
    }

    public List<IncidenciaDTO> obtenerIncidenciaTipoEnPeriodo(String claveTipo,
            String fechaInicial, String fechaFinal) throws RuntimeException {

        OffsetDateTime fechaInicio = DateUtil.stringToOffSetTime(fechaInicial);
        OffsetDateTime fechaFin = DateUtil.stringToOffSetTime(fechaFinal);

        fechaInicio = DateUtil.resetOffSetTime(fechaInicio);
        fechaFin = DateUtil.setToEndOfDay(fechaFin);

        if (fechaInicio.isAfter(fechaFin)) {
            OffsetDateTime fechaAux = fechaFin;
            fechaFin = fechaInicio;
            fechaInicio = fechaAux;
        }

        List<Incidencia> listaOriginal = incidenciaRepo.findByTipoEnPeriodo(claveTipo, fechaInicio, fechaFin);
        log.info("Se recuperaron {} incidencias del repositorio", listaOriginal.size());

        List<IncidenciaDTO> incidenciasDTO = listaOriginal
                .stream()
                .peek(incidencia -> log.debug("Procesando incidencia con tipo: {}", incidencia.getTipo().getClave()))
                .filter(incidencia -> {
                    String clave = incidencia.getTipo().getClave();
                    if ("PR".equals(clave)) {
                        boolean activo = incidencia.getSolicitudPrenda().getPrenda().getActivo();
                        log.debug("Incidencia tipo PR - Prenda activa: {}", activo);
                        return activo;
                    } else if ("A".equals(clave)) {
                        boolean activo = incidencia.getSolicitudArticulo().getArticulo().getActivo();
                        log.debug("Incidencia tipo A - Artículo activo: {}", activo);
                        return activo;
                    }
                    return true; // Otros tipos pasan sin filtro
                })
                .map(this::convertir)
                .collect(Collectors.toList());

        log.info("Total de incidencias válidas después del filtro: {}", incidenciasDTO.size());

        if (incidenciasDTO.isEmpty()) {
            log.warn("No existen registros válidos para el periodo {} a {} con tipo {}", fechaInicio, fechaFin,
                    claveTipo);
            throw new RuntimeException("No existen registros");
        }

        return incidenciasDTO;
    }

    public IncidenciaPermisoDTO actualizarEstatusIncidencia(Integer id, IncidenciaPermisoDTO body) {

        log.info("Iniciando el actualizado de una incidencia");
        Empleado empleadoRevision = empleadoRepo.findByNumeroEmpleado(body.getEmpleadoRev())
                .orElseThrow(() -> new RuntimeException("No se encontro registro de empleado con ese indentificador"));
        
        Incidencia incidencia = incidenciaRepo.buscarPorIdIncidenciaPermiso(id)
                .orElseThrow(() -> new RuntimeException("No existe incidencia con ese identificador"));
        
        SolicitudPermiso solicitudPermiso = solicitudPermisoRepo
                .findById(incidencia.getSolicitudPermiso().getIdSolicitud())
                .orElseThrow(() -> new RuntimeException("No se encontro registro de solicitud permiso"));
        
        BigDecimal valor = null;
        String sGoceSueldo = "100.0";
        Boolean goceSueldo = solicitudPermiso.getEmpleadoSol().getEmpleadoConfiguracion().getGoceSueldo();
        
        if (Boolean.TRUE.equals(goceSueldo) && sGoceSueldo != null && !sGoceSueldo.isEmpty()) {
            valor = new BigDecimal(sGoceSueldo);
        } else {
            valor = BigDecimal.ZERO;
        }

        if (body.getCodigoEstado().matches(TE_RECHAZADO)) {
            solicitudPermiso.setDescripcionRechazo(body.getDescripcionRechazo());
        }
        solicitudPermiso.setGoceSueldo(valor);
        solicitudPermiso.setFechaMod(OffsetDateTime.now());
        solicitudPermiso.setEmpleadoRev(empleadoRevision);
        solicitudPermiso.setEstatusSolicitud(estatusSoliciturRepo.buscarPorClave(body.getCodigoEstado())
                .orElseThrow(() -> new RuntimeException("No se encontro registro de estatus solicitud")));
        solicitudPermisoRepo.save(solicitudPermiso);

        incidencia.setFechaModificacion(OffsetDateTime.now());
        incidencia.setEmpleadoRevisa(empleadoRevision);
        incidencia.setEstatus(estatusIncidenciaRepo.findByClave(body.getCodigoEstado()).orElseThrow(
                () -> new RuntimeException("No existe estus con esa clave: " + body.getCodigoEstado())));

        if (incidencia.getEstatus().getClave().trim().matches(TE_ACEPTADO)) {
            this.guardarRegistroVacaciones(incidencia.getEmpleadoSol(), incidencia);
        }

        incidenciaRepo.save(incidencia);
        log.info("Se actualizo correctamente la incidencia");
        return incidenciaPermisoMapper.toDTO(incidencia);
    }

    public IncidenciaDTO convertir(Incidencia incidencia) {
        return incidenciaMapper.toDTO(incidencia);
    }

    public IncidenciaPermisoDTO convertirPermiso(Incidencia incidencia) {
        return incidenciaPermisoMapper.toDTO(incidencia);
    }

    public void guardarRegistroVacaciones(Empleado empleado, Incidencia incidencia) {
        log.info("Iniciando el guardado de las vacaciones");
        this.empleadoTieneDiasLaborales(empleado);

        int cantidadRegistrosGuardados = 0;
        InformacionEmpresa empleadoEmpresa = empleado.getInformacionEmpresa();
        EstadoRegistro estadoRegistro = null;
        EstadoRegistro registroFalta = this.estatusFalta();

        String tipoIncidencia = incidencia.getSolicitudPermiso().getTipoSolicitud().getClave();

        Integer horaEntrada = DateUtil.getHora(empleadoEmpresa.getHoraEntrada());
        Integer horaSalida = DateUtil.getHora(empleadoEmpresa.getHorasalida());

        List<OffsetDateTime> listaFechas = incidencia.getSolicitudPermiso().getDiasPermiso()
                .stream()
                .map(item -> item.getFecha())
                .sorted()
                .collect(Collectors.toList());

        for (OffsetDateTime dia : listaFechas) {
            RegistroAsistencia registro = null;
            
            Date entrada = DateUtil.offsetDateTimeToDate(dia);
            
            Optional<RegistroAsistencia> registroPasado = buscarRegistroAusencia(empleado.getIdEmpleado(), dia, dia, registroFalta.getCodigo());
            
            if(registroPasado.isPresent()){
                registro = registroPasado.get();
                log.info("Modificando la ausencia del empleado {} con fecha: {}", empleado.getIdEmpleado(), registroPasado.get().getFechaEntrada().toString());
            } else {
                registro = new RegistroAsistencia();
                registro.setEmpleado(empleado);
                
                Date registroEntrada = DateUtil.getDateTime(DateUtil.getAnio(entrada), DateUtil.getMes(entrada), DateUtil.getDia(entrada), horaEntrada, 0, 0, 0);
                log.trace("Dia hora entrada: {}", registroEntrada);
                registro.setFechaEntrada(DateUtil.dateToOffsetDateTime(registroEntrada));

                Date registroSalida = DateUtil.getDateTime(DateUtil.getAnio(entrada), DateUtil.getMes(entrada), DateUtil.getDia(entrada), horaSalida, 0, 0, 0);
                log.trace("Dia hora salida: {}", registroSalida);
                registro.setFechaSalida(DateUtil.dateToOffsetDateTime(registroSalida));
                log.info("Registrando al empleado {} con el dia de vacaciones: {}", empleado.getIdEmpleado(), registroEntrada.toString());
            }
            
            switch (tipoIncidencia) {
                case TP_VACACIONES:
                    estadoRegistro = this.estatusVacaciones();
                    registro.setStatus(estadoRegistro);
                    asistenciaRepo.save(registro);
                    
                    RegistroVacaciones registroVacaciones = new RegistroVacaciones();
                    registroVacaciones.setRegistroAsistencia(registro);
                    registroVacaciones.setVacaciones(incidencia.getSolicitudPermiso().getVacaciones());
                    registroVacacionesRepo.save(registroVacaciones);
                    log.info("Terminando de guardar 1 registro de vacaciones");
                    break;
                case TP_PERMISO:
                    estadoRegistro = this.estatusPermiso();
                    registro.setStatus(estadoRegistro);
                    asistenciaRepo.save(registro);
                    log.info("Terminando de guardar 1 registro de permiso");
                    break;
            }
            
            cantidadRegistrosGuardados += 1;
        }

        log.info("Num. registros de vacaciones / permisos guardados del empleado {} en asistencia: {}", empleado.getIdEmpleado(), cantidadRegistrosGuardados);
    }
    
    public Optional<RegistroAsistencia> buscarRegistroAusencia(Integer idEmpleado, OffsetDateTime fechaInicio, OffsetDateTime fechaFin, String codigo){
        OffsetDateTime inicio = DateUtil.setHourTime(fechaInicio, 0, 0, 0, 0);
        OffsetDateTime fin = DateUtil.setHourTime(fechaFin, 0, 0, 0, 0);
        return asistenciaRepo.buscarPorPeriodoAusencia(idEmpleado, inicio, fin, codigo);
    }

    public void empleadoTieneDiasLaborales(Empleado empleado) {
        if (empleado == null) {
            throw new RuntimeException("Error: El empleado no tiene informacion");
        }

        if (empleado.getInformacionEmpresa() == null) {
            throw new RuntimeException("Erro: El empleado no tiene informacion empresarial");
        }

        if (empleado.getInformacionEmpresa().getDiaLunes() == false
                || empleado.getInformacionEmpresa().getDiaMartes() == false
                || empleado.getInformacionEmpresa().getDiaMiercoles() == false
                || empleado.getInformacionEmpresa().getDiaJueves() == false
                || empleado.getInformacionEmpresa().getDiaViernes() == false) {
            throw new RuntimeException("Error: No tiene dias laborales asignados. Por favor contactar a RH");
        }

    }

    public EstadoRegistro estatusPermiso() {
        String permiso = TP_PERMISO;
        Optional<EstadoRegistro> estatusPermiso = estadoRegistroRepo.findByCodigo(permiso);
        return estatusPermiso.get();
    }

    public EstadoRegistro estatusVacaciones() {
        String vacaciones = TP_VACACIONES;
        Optional<EstadoRegistro> estatusVacaciones = estadoRegistroRepo.findByCodigo(vacaciones);
        return estatusVacaciones.get();
    }
    
    public EstadoRegistro estatusFalta() {
        String falta = TP_FALTA;
        Optional<EstadoRegistro> estatusVacaciones = estadoRegistroRepo.findByCodigo(falta);
        return estatusVacaciones.get();
    }

    public List<OffsetDateTime> diasDeAsueto() {
        Integer anioEnCurso = DateUtil.getAnio(new Date());
        OffsetDateTime fechaInicio = DateUtil.inicializaFechaInicioAnioCurso(anioEnCurso - 1);
        OffsetDateTime fechaFin = DateUtil.inicializaFechaTerminoAnioCurso(anioEnCurso + 1);

        List<DiaNoLaboral> diasNoLaboral = diaNoLaboralRepo.buscarPorPeriodo("MX", fechaInicio, fechaFin);
        List<OffsetDateTime> diasDeAsueto = new ArrayList();

        for (DiaNoLaboral aux : diasNoLaboral) {
            diasDeAsueto.add(aux.getFecha());
        }

        return diasDeAsueto;
    }

    private void validarIncidenciaTieneEmpleado(Incidencia incidencia) {
        if (incidencia.getEmpleadoSol() == null) {
            throw new IllegalStateException("La incidencia no contiene empleado solicitante");
        }
    }

    private void validarIncidenciaEsVacaciones(Incidencia incidencia) {
        SolicitudPermiso solicitud = incidencia.getSolicitudPermiso();
        if (solicitud == null || solicitud.getTipoSolicitud() == null) {
            throw new IllegalStateException("La incidencia no tiene una solicitud de permiso válida");
        }

        String clave = solicitud.getTipoSolicitud().getClave();
        if (!TP_VACACIONES.equalsIgnoreCase(clave)) {
            throw new IllegalArgumentException("El código de registro de la incidencia no es de vacaciones");
        }
    }

    private void validarPeriodoVacacionalActivo(List<RegistroAsistencia> asistencias) {
        OffsetDateTime ultimoDia = asistencias.get(asistencias.size() - 1).getFechaSalida();
        OffsetDateTime hoy = OffsetDateTime.now();

        DateUtil.setToEndOfDay(ultimoDia);
        DateUtil.setToEndOfDay(hoy);

        if (ultimoDia.isBefore(hoy)) {
            throw new IllegalStateException("El periodo vacacional ya ha terminado");
        }
    }

    private void validarPeriodoVacacionalEnCurso(List<RegistroAsistencia> asistencias, Incidencia incidencia, String empleadoRevisor) {
        OffsetDateTime registroPrimerDia = asistencias.get(0).getFechaSalida();
        OffsetDateTime registroUltimoDia = asistencias.get(asistencias.size() - 1).getFechaSalida();
        OffsetDateTime fechaHoy = OffsetDateTime.now();

        OffsetDateTime primerDia = DateUtil.setToEndOfDay(registroPrimerDia);
        OffsetDateTime ultimoDia = DateUtil.setToEndOfDay(registroUltimoDia);
        OffsetDateTime hoy = DateUtil.setToEndOfDay(fechaHoy);

        if (primerDia.isBefore(hoy) && ultimoDia.isAfter(hoy)) {
            actualizarEstatusIncidencia(incidencia, empleadoRevisor);
            throw new IllegalStateException("El periodo vacacional esta en curso");
        }
    }

    private void eliminarAsistenciasSiCorresponde(List<RegistroAsistencia> asistencias) {
        OffsetDateTime registroPrimerDia = asistencias.get(0).getFechaSalida();
        OffsetDateTime fechaHoy = OffsetDateTime.now();

        OffsetDateTime primerDia = DateUtil.setToEndOfDay(registroPrimerDia);
        OffsetDateTime hoy = DateUtil.setToEndOfDay(fechaHoy);

        log.info("Inicia proceso de eliminación de registros del periodo vacacional");

        if (primerDia.isAfter(hoy)) {
            List<RegistroVacaciones> registrosVacaciones = asistencias.stream()
                    .map(RegistroAsistencia::getRegistroVacaciones).collect(Collectors.toList());

            registrosVacaciones.stream()
                    .filter(Objects::nonNull)
                    .forEach(registroVacacionesRepo::delete);

            asistencias.forEach(asistenciaRepo::delete);
        }

        log.info("Finaliza proceso de eliminación de registros del periodo vacacional");
    }

    private void actualizarEstatusIncidencia(Incidencia incidencia, String numeroEmpleadoRevisante) {
        log.info("Inicia actualización de estatus de la incidencia y la solicitud");

        Empleado empleadoRev = empleadoRepo.findByNumeroEmpleado(numeroEmpleadoRevisante)
                .orElseThrow(() -> new RuntimeException("No existe empleado con el número " + numeroEmpleadoRevisante));

        OffsetDateTime fechaMod = OffsetDateTime.now();

        incidencia.setEstatus(
                estatusIncidenciaRepo.findByClave("C")
                        .orElseThrow(() -> new RuntimeException("No existe estatus de incidencia con la clave 'C'"))
        );
        incidencia.setEmpleadoRevisa(empleadoRev);
        incidencia.setFechaModificacion(fechaMod);

        SolicitudPermiso solicitud = incidencia.getSolicitudPermiso();
        EstatusSolicitud estatusSolicitud =  estatusSoliciturRepo.buscarPorClave("C")
                        .orElseThrow(() -> new RuntimeException("No existe estatus de solicitud con la clave 'C'")
        );
        solicitud.setEstatusSolicitud(estatusSolicitud);
               
        solicitud.setEmpleadoRev(empleadoRev);
        solicitud.setFechaMod(fechaMod);
        
        solicitudPermisoRepo.save(solicitud);

        incidenciaRepo.save(incidencia);

        log.info("Finaliza actualización de estatus de la incidencia y la solicitud");
    }

    public IncidenciaPermisoDTO eliminarRegistroVacaciones(Integer id, IncidenciaPermisoDTO body) {
        // 1. Obtener incidencia
        Incidencia incidencia = incidenciaRepo.buscarPorIdIncidenciaPermiso(id)
                .orElseThrow(() -> new RuntimeException("No existe incidencia con ese identificador"));

        // 2. Validar que la incidencia esté correctamente configurada
        validarIncidenciaTieneEmpleado(incidencia);
        validarIncidenciaEsVacaciones(incidencia);

        // 3. Obtener fechas de la solicitud de permiso
        OffsetDateTime inicioVacaciones = incidencia.getSolicitudPermiso().getFechaInicio();
        OffsetDateTime finVacaciones = incidencia.getSolicitudPermiso().getFechaFin();

        OffsetDateTime inicio = DateUtil.setHourTime(inicioVacaciones, 0, 0, 0, 0);
        OffsetDateTime fin = DateUtil.setToEndOfDay(finVacaciones);

        // 4. Obtener registros de asistencia
        Empleado empleado = incidencia.getEmpleadoSol();
        String codigo = incidencia.getSolicitudPermiso().getTipoSolicitud().getClave();
        List<RegistroAsistencia> asistencias = asistenciaRepo.buscarPorPeriodoSolicitud(
                empleado.getIdEmpleado(), codigo, inicio, fin
        );

        if (asistencias.isEmpty()) {
            throw new IllegalStateException("El registro de las vacaciones de la incidencia está vacío");
        }

        // 5. Validar si el periodo vacacional ya terminó
        validarPeriodoVacacionalActivo(asistencias);

        // 6. Validar si el perido vacional esta en curso
        validarPeriodoVacacionalEnCurso(asistencias, incidencia, body.getEmpleadoRev());

        // 7. Eliminar registros si aplica
        eliminarAsistenciasSiCorresponde(asistencias);

        // 8. Actualizar estatus
        actualizarEstatusIncidencia(incidencia, body.getEmpleadoRev());

        return convertirPermiso(incidencia);
    }
}
