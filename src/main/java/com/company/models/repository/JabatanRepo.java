package com.company.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.models.entities.Jabatan;

public interface JabatanRepo extends JpaRepository<Jabatan, Integer> {
    
}
