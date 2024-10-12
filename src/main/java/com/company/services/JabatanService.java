package com.company.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.models.entities.Jabatan;
import com.company.models.repository.JabatanRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class JabatanService {
    
    @Autowired
    private JabatanRepo jabatanRepo;

    public Jabatan create(Jabatan jabatan) {
        return jabatanRepo.save(jabatan);
    }

    public List<Jabatan> find(Integer id, String namaJabatan) {
        List<Jabatan> jabatans = new ArrayList<>();
        jabatanRepo.findAll().forEach(jabatans::add);

        List<Jabatan> filteredJabatans = jabatans.stream()
            .filter(jabatan -> (id == null || jabatan.getId().equals(id)))
            .filter(jabatan -> (namaJabatan == null || jabatan.getnamaJabatan().equalsIgnoreCase(namaJabatan)))
            .collect(Collectors.toList());

        if(filteredJabatans.isEmpty()) {
            new EntityNotFoundException("Jabatan not found");
        }

        return filteredJabatans;
    }

    private Jabatan findOne(Integer id) {
        return jabatanRepo.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Jabatan with ID " + id + " not found")
        );
    }

    public Jabatan updateOne(Integer id, Jabatan jabatan) {
        Jabatan existingJabatan = this.findOne(id);

        existingJabatan.setnamaJabatan(jabatan.getnamaJabatan());

        return jabatanRepo.save(existingJabatan);
    }

    public void removeOne(Integer id) {
        Jabatan existingJabatan = this.findOne(id);

        jabatanRepo.delete(existingJabatan);
    }
}
