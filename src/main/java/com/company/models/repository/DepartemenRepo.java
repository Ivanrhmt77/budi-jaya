package com.company.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.models.entities.Departemen;

public interface DepartemenRepo extends JpaRepository<Departemen, Integer> {
    
}
