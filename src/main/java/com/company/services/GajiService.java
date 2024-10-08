package com.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.models.entities.Gaji;
import com.company.models.repository.GajiRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class GajiService {
    
    @Autowired
    private GajiRepo gajiRepo;

    public Gaji create(Gaji gaji) {
        return gajiRepo.save(gaji);
    }

    public Gaji updateOne(Integer id, Gaji gaji) {
        Gaji existingGaji = this.findOne(id);

        existingGaji.setKaryawanId(gaji.getKaryawanId());
        existingGaji.setBulan(gaji.getBulan());
        existingGaji.setGajiPokok(gaji.getGajiPokok());
        existingGaji.setPotongan(gaji.getPotongan());
        existingGaji.setTotalGaji(gaji.getTotalGaji());
        existingGaji.setTunjangan(gaji.getTunjangan());

        return gajiRepo.save(existingGaji);
    }

    public Gaji findOne(Integer id) {
        return gajiRepo.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Absensi with ID " + id + " not found")
        );
    }

    public Iterable<Gaji> findAll() {
        return gajiRepo.findAll();
    }

    public void removeOne(Integer id) {
        if (!gajiRepo.existsById(id)) {
            throw new EntityNotFoundException("Absensi with ID " + id + " not found");
        }

        gajiRepo.deleteById(id);
    }
}
