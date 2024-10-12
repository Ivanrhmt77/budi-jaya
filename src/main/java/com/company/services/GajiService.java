package com.company.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.models.entities.Gaji;
import com.company.models.entities.Karyawan;
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

    public List<Gaji> find(Integer id, Karyawan karyawanId, String bulan, Double gajiPokok, Double tunjangan, Double potongan, Double totalGaji) {
        List<Gaji> gajis = new ArrayList<>();
        gajiRepo.findAll().forEach(gajis::add);

        List<Gaji> filteredGajis = gajis.stream()
            .filter(gaji -> (id == null || gaji.getId().equals(id)))
            .filter(gaji -> (karyawanId == null || gaji.getKaryawanId().equals(karyawanId)))
            .filter(gaji -> (bulan == null || gaji.getBulan().equalsIgnoreCase(bulan)))
            .filter(gaji -> (gajiPokok == null || gaji.getGajiPokok().equals(gajiPokok)))
            .filter(gaji -> (tunjangan == null || gaji.getTunjangan().equals(tunjangan)))
            .filter(gaji -> (potongan == null || gaji.getPotongan().equals(potongan)))
            .filter(gaji -> (totalGaji == null || gaji.getTotalGaji().equals(totalGaji)))
            .collect(Collectors.toList());
        
        if(filteredGajis.isEmpty()) {
            throw new EntityNotFoundException("Gaji not found");
        }
        
        return filteredGajis;
    }

    private Gaji findOne(Integer id) {
        return gajiRepo.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Gaji with ID " + id + " not found")
        );
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

    public void removeOne(Integer id) {
        Gaji existingGaji = this.findOne(id);

        gajiRepo.delete(existingGaji);
    }
}
