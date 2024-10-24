package com.company.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.models.entities.Absensi;
import com.company.models.entities.Karyawan;
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
        absensi.setWaktuKeluar(null);
        absensi.setWaktuMasuk(null);
        absensi.setStatus(null);

        return absensiRepo.save(absensi);
    }

    public List<Absensi> find(Integer id, Karyawan karyawanId, Date tanggal, LocalTime waktuMasuk, LocalTime waktuKeluar, StatusAbsensi status) {
        List<Absensi> absensis = new ArrayList<>();
        absensiRepo.findAll().forEach(absensis::add);

        List<Absensi> filteredAbsensis = absensis.stream()
            .filter(absensi -> (id == null || absensi.getId().equals(id)))
            .filter(absensi -> (karyawanId == null || absensi.getKaryawanId().equals(karyawanId)))
            .filter(absensi -> (tanggal == null || absensi.getTanggal().equals(tanggal)))
            .filter(absensi -> (waktuMasuk == null || absensi.getWaktuMasuk().equals(waktuMasuk)))
            .filter(absensi -> (waktuKeluar == null || absensi.getWaktuKeluar().equals(waktuKeluar)))
            .filter(absensi -> (status == null || absensi.getStatus().equals(status)))
            .collect(Collectors.toList());

        filteredAbsensis.forEach(absensi -> {
            if(absensi.getStatus() == null && absensi.getTanggal().before(new Date())) {
                absensi.setStatus(StatusAbsensi.ALPHA);
                absensiRepo.save(absensi);
            }
        });

        if(filteredAbsensis.isEmpty()) {
            throw new EntityNotFoundException("Absensi not found");
        }

        return filteredAbsensis;
    }

    private Absensi findOne(Integer id) {
        return absensiRepo.findById(id).orElseThrow(() -> 
            new EntityNotFoundException("Absensi with ID " + id + " not found")
        );
    }

    public Absensi updateOne(Integer id, Absensi absensi) {
        Absensi existingAbsensi = this.findOne(id);

        if(absensi.getStatus().equals(StatusAbsensi.HADIR)) {
            if(existingAbsensi.getWaktuMasuk() == null)
                absensi.setWaktuMasuk(LocalTime.now());
            else if(existingAbsensi.getWaktuKeluar() == null)
                absensi.setWaktuKeluar(LocalTime.now());
        }

        if(absensi.getKaryawanId() != null) existingAbsensi.setKaryawanId(absensi.getKaryawanId());
        if(absensi.getStatus() != null) existingAbsensi.setStatus(absensi.getStatus());
        if(absensi.getTanggal() != null) existingAbsensi.setTanggal(absensi.getTanggal());
        if(absensi.getWaktuKeluar() != null) existingAbsensi.setWaktuKeluar(absensi.getWaktuKeluar());
        if(absensi.getWaktuMasuk() != null) existingAbsensi.setWaktuMasuk(absensi.getWaktuMasuk());
        
        return absensiRepo.save(existingAbsensi);
    }

    public void removeOne(Integer id) {
        Absensi existingAbsensi = this.findOne(id);

        absensiRepo.delete(existingAbsensi);
    }
    
}
