package com.company.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.models.entities.Jabatan;

public interface JabatanRepo extends CrudRepository<Jabatan, Integer> {
    
}
