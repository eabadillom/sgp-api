package com.ferbo.sgp.api.service;

import java.util.List;
import java.util.stream.Collectors;
import com.ferbo.sgp.api.dto.PlantaDTO;
import com.ferbo.sgp.api.mapper.PlantaMapper;
import com.ferbo.sgp.api.model.Planta;
import com.ferbo.sgp.api.repository.PlantaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantaSrv 
{
    @Autowired
    private PlantaRepo plantaRepo;
    
    @Autowired
    private PlantaMapper plantaMapper;
    
    public List<PlantaDTO> buscarPlantas() 
    {
        return plantaRepo.findByActivo()
            .stream()
            .map(this::convertirPlantas).collect(Collectors.toList());
    }
    
    private PlantaDTO convertirPlantas(Planta planta) 
    {
        return plantaMapper.toDTO(planta);
    }
    
}
