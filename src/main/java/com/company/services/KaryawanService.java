package com.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.models.entities.Karyawan;
import com.company.models.repository.KaryawanRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class KaryawanService {
    
    @Autowired
    private KaryawanRepo karyawanRepo;

    public Karyawan save(Karyawan karyawan) {
        return karyawanRepo.save(karyawan);
    }

    public Karyawan findOne(Integer id) {
        return karyawanRepo.findById(id).get();
    }

    public Iterable<Karyawan> findAll() {
        return karyawanRepo.findAll();
    }

    public void removeOne(Integer id) {
        karyawanRepo.deleteById(id);
    }
}
