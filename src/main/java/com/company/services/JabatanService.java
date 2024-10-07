package com.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.models.entities.Jabatan;
import com.company.models.repository.JabatanRepo;
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
        return jabatanRepo.findById(id).get();
    }

    public Iterable<Jabatan> findAll() {
        return jabatanRepo.findAll();
    }

    public void removeOne(Integer id) {
        jabatanRepo.deleteById(id);
    }
}
