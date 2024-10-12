package com.company.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.models.entities.Absensi;
import com.company.models.entities.enums.StatusAbsensi;
import com.company.models.repository.AbsensiRepo;
import jakarta.persistence.EntityNotFoundException;
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
        return absensiRepo.findById(id).orElseThrow(() -> 
            new EntityNotFoundException("Absensi with ID " + id + " not found")
        );
    }

    public Iterable<Absensi> findAll() {
        return absensiRepo.findAll();
    }

    public Iterable<Absensi> findByTanggal(Date tanggal) {
        return absensiRepo.findByTanggal(tanggal);
    }

    public Iterable<Absensi> findByStatus(StatusAbsensi status) {
        return absensiRepo.findByStatus(status);
    }

    public void removeOne(Integer id) {
        if (!absensiRepo.existsById(id)) {
            throw new EntityNotFoundException("Absensi with ID " + id + " not found, unable to delete");
        }
        
        absensiRepo.deleteById(id);
    }
    
}
