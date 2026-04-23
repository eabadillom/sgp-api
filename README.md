# SGP-API
API para el Sistema de Gestión de Personal

## Recursos disponibles

### FP-Client
* /fp-client/empleado
* /fp-client/empleado/numero

#### Notificación SGP
* /fp-client/enviar/notificacion

### Gestion
* /gestion/{numero}/fotografia
* /gestion/{numero}/biometrico
* /gestion/empleado

### Movil
* /movil/generar
* /movil/verificar
* /movil/deshabilitar

#### Incapacidades
* /movil/incapacidades/{fechaInicio}/{fechaFin}
* /movil/incapacidad/{id}
* /movil/incapacidad/{numEmpleado}/cancelar
* /movil/incapacidad/empleados
* /movil/incapacidad/tiposIncapacidades
* /movil/incapacidad/controlIncapacidades
* /movil/incapacidad/riesgosTrabajos
* /movil/incapacidad/tiposRiesgos
* /movil/incapacidad/guardar

#### Incidencias
* /movil/incidencias/{tipo}/{fechaIni}/{fechaFin}
* /movil/incidencias/{id}/estatus
* /movil/incidencia/{id}

#### Notificación Movil
* /movil/generar/notificacion

#### Registro de asistencia
* /movil/registros/{fechaIni}/{fechaFin}/{estatus}
* /movil/registros/{id}/estatus

#### Solicitudes
* /movil/solicitudes/{id}/estatus

#### Empleados
* /movil/empleado/{numero}
