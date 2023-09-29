package com.ferbo.sgp.api.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ferbo.sgp.api.models.DetBiometricoModel;


@Repository
public interface DetBiometricoRepository extends CrudRepository<DetBiometricoModel,Integer> {
    public abstract DetBiometricoModel findByIdEmpleado(Integer idEmpleado);
    

}
