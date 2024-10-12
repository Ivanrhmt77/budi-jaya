package com.company.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.models.entities.Karyawan;

public interface KaryawanRepo extends CrudRepository<Karyawan, Integer> {
    List<Karyawan> findByEmail(String email);
}
