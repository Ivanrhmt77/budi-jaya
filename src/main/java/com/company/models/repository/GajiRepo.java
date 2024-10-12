package com.company.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.models.entities.Gaji;

public interface GajiRepo extends CrudRepository<Gaji, Integer> {
    
}
