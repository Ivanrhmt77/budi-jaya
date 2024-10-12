package com.company.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.models.entities.Absensi;

public interface AbsensiRepo extends CrudRepository<Absensi, Integer> {
    
}
