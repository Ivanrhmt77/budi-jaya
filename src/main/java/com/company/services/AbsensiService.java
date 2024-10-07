package com.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.models.entities.Absensi;
import com.company.models.repository.AbsensiRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AbsensiService {
    
    @Autowired
    private AbsensiRepo absensiRepo;

    public Absensi create(Absensi absensi) {
        return absensiRepo.save(absensi);
    }

    public Absensi updateOne(Integer id, Absensi absensi) {
        Absensi existingAbsensi = this.findOne(id);

        existingAbsensi.setKaryawanId(absensi.getKaryawanId());
        existingAbsensi.setStatus(absensi.getStatus());
        existingAbsensi.setTanggal(absensi.getTanggal());
        existingAbsensi.setWaktuKeluar(absensi.getWaktuKeluar());
        existingAbsensi.setWaktuMasuk(absensi.getWaktuMasuk());
        
        return absensiRepo.save(existingAbsensi);
    }

    public Absensi findOne(Integer id) {
        return absensiRepo.findById(id).get();
    }

    public Iterable<Absensi> findAll() {
        return absensiRepo.findAll();
    }

    public void removeOne(Integer id) {
        absensiRepo.deleteById(id);
    }
}
