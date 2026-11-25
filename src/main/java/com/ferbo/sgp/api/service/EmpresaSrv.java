package com.ferbo.sgp.api.service;

import java.util.List;
import java.util.stream.Collectors;
import com.ferbo.sgp.api.dto.EmpresaDTO;
import com.ferbo.sgp.api.mapper.EmpresaMapper;
import com.ferbo.sgp.api.model.Empresa;
import com.ferbo.sgp.api.repository.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaSrv 
{
    @Autowired
    private EmpresaRepo empresaRepo;
    
    @Autowired
    private EmpresaMapper empresaMapper;
    
    public List<EmpresaDTO> buscarEmpresas() {
        return empresaRepo.findByActivo()
            .stream()
            .map(this::convertirEmpresa).collect(Collectors.toList());
    }
    
    private EmpresaDTO convertirEmpresa(Empresa empleado) 
    {
        return empresaMapper.toDTO(empleado);
    }
    
}
