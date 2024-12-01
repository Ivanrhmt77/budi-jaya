package com.company.models.repository;

import com.company.models.entities.Akun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AkunRepo extends JpaRepository<Akun, Integer> {
    Optional<Akun> findByEmail(String email);
}