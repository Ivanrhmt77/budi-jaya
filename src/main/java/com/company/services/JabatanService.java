package com.company.services;

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

    public Jabatan updateOne(Integer id, Jabatan jabatan) {
        Jabatan existingJabatan = this.findOne(id);

        existingJabatan.setnamaJabatan(jabatan.getnamaJabatan());

        return jabatanRepo.save(existingJabatan);
    }

    public Jabatan findOne(Integer id) {
        return jabatanRepo.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Absensi with ID " + id + " not found")
        );
    }

    public Iterable<Jabatan> findAll() {
        return jabatanRepo.findAll();
    }
    
    public Iterable<Jabatan> findByNamaJabatan(String namaJabatan) {
        return jabatanRepo.findByNamaJabatan(namaJabatan);
    }

    public void removeOne(Integer id) {
        if(jabatanRepo.existsById(id)) {
            throw new EntityNotFoundException("Absensi with ID " + id + " not found");
        }

        jabatanRepo.deleteById(id);
    }
}
