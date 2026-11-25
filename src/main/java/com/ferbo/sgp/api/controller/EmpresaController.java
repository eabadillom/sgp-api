package com.ferbo.sgp.api.controller;

import java.util.List;
import com.ferbo.sgp.api.dto.EmpresaDTO;
import com.ferbo.sgp.api.service.EmpresaSrv;
import com.ferbo.sgp.api.tool.ErrorResponseBuilder;
import com.ferbo.tools.exception.RuleException;
import com.ferbo.tools.exception.SystemException;
import com.ferbo.tools.exception.ToolException;
import com.ferbo.tools.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movil")
public class EmpresaController 
{
    private static Logger log = LogManager.getLogger(EmpresaController.class);
    private static final String TIPO_ERROR_ACCESO = "Acceso";
    
    @Autowired
    private EmpresaSrv empresaSrv;
    
    @GetMapping("empresas/obtenerEmpresas")
    public ResponseEntity<?> listarEmpresas() 
    {
        List<EmpresaDTO> listEmpresasDTO = null;
        
        try {
            log.info("Inicia proceso para buscar las empresas activas");
            listEmpresasDTO = empresaSrv.buscarEmpresas();
            log.info("Finaliza proceso para buscar las empresas activas");
        }catch (ToolException ex) {
            log.info("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNAUTHORIZED, "Herramienta auxiliar", ex);
        } catch (ValidationException ex) {
            log.info("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.UNPROCESSABLE_ENTITY, "Validación", ex);
        } catch (SystemException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.NOT_FOUND, "Servicio externo", ex);
        } catch (RuleException ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorMovil(HttpStatus.CONFLICT, "Regla de negocio", ex);
        } catch (Exception ex) {
            log.warn("Error: {}", ex.getMessage(), ex);
            return ErrorResponseBuilder.construirErrorDesdeApiToMovil(ex);
        }
        
        return ResponseEntity.ok(listEmpresasDTO);
    }
    
}
