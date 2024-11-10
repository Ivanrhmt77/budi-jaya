package com.company.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.models.entities.Departemen;
import com.company.models.entities.Jabatan;
import com.company.models.entities.enums.JabatanLevel;
import com.company.models.repository.JabatanRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class JabatanService {
    
    @Autowired
    private JabatanRepo jabatanRepo;

    public List<Jabatan> create(List<Jabatan> jabatanList) {
        return jabatanRepo.saveAll(jabatanList);
    }

    public List<Jabatan> find(Integer id, String namaJabatan, JabatanLevel level, Departemen departemen) {
        List<Jabatan> jabatans = new ArrayList<>();
        jabatanRepo.findAll().forEach(jabatans::add);

        List<Jabatan> filteredJabatans = jabatans.stream()
            .filter(jabatan -> (id == null || jabatan.getId().equals(id)))
            .filter(jabatan -> (namaJabatan == null || jabatan.getNamaJabatan().equalsIgnoreCase(namaJabatan)))
            .filter(jabatan -> (level == null || jabatan.getLevel().equals(level)))
            .filter(jabatan -> (departemen == null || jabatan.getDepartemen().equals(departemen)))
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

        if(jabatan.getNamaJabatan() != null) existingJabatan.setNamaJabatan(jabatan.getNamaJabatan());
        if(jabatan.getLevel() != null) existingJabatan.setLevel(jabatan.getLevel());
        if(jabatan.getDepartemen() != null) existingJabatan.setDepartemen(jabatan.getDepartemen());

        return jabatanRepo.save(existingJabatan);
    }

    public void removeOne(Integer id) {
        Jabatan existingJabatan = this.findOne(id);

        jabatanRepo.delete(existingJabatan);
    }
}
