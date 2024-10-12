package com.company.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.models.entities.Departemen;

public interface DepartemenRepo extends CrudRepository<Departemen, Integer> {
    
}
