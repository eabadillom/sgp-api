# Changelog

Todos los cambios importantes en este proyecto serán documentados en este archivo.

Este proyecto sigue versionado semántico (SemVer) y el formato de [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/).

---
## [1.3.0] - 2026-11-25
### Added
* Se agrega endpoint para la consulta de empleados que tienen registro de asistencias durante un periodo.
* Se agrega endpoint para la consulta de detalle del empleado donde se muestra los dias laborados segun su indidencias de asistencia.
* Se agrega endpoint para mostrar las plantas activas.
* Se agrega endpoint para mostrar las empresas activas.
* Se agrega endpoint para mostrar los estatus de las asistencias

### Change
* Cuando se valida que el token ya esta expirado, cambiarle el status a no valido para dejarlo de usar.

## [1.2.0] - 2026-11-12
### Added
* Se agrega endpoint para el cambio de contraseña del movil de sgp.

## [1.1.1] - 2026-06-28
* Se agrega controlador para dispositivos móviles que requieren autenticación, pero no acceso a los endpoints del sistema.

## [1.1.0] - 2026-08-19

### Added
* Se agrega end-point para la consulta de 1 cliente sistema

## [1.0.0] - 2026-04-17

### Added
* Se agrega end-point para la peticion de 1 empleado con numero de usuario
* Se agregan archivos de documentación del proyecto (README.md, CHANGELOG.md y LICENSE).

### Changed
* Se actualiza dependencia de `mysql-connector-j` de `8.0.33` a `8.4.0`.

## Nota
Los cambios previos a esta versión (desde el inicio del proyecto) no están documentados por tratarse de la fase inicial de desarrollo.
