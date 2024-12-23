package com.company.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
        absensi.setStatusAbsensi(null);

        return absensiRepo.save(absensi);
    }

    public List<Absensi> find(Integer id, Karyawan karyawanId, LocalDate tanggal, LocalTime waktuMasuk, LocalTime waktuKeluar, StatusAbsensi status) {
        List<Absensi> absensis = new ArrayList<>();
        absensiRepo.findAll().forEach(absensis::add);

        List<Absensi> filteredAbsensis = absensis.stream()
            .filter(absensi -> (id == null || absensi.getId().equals(id)))
            .filter(absensi -> (karyawanId == null || absensi.getKaryawan().equals(karyawanId)))
            .filter(absensi -> (tanggal == null || absensi.getTanggal().equals(tanggal)))
            .filter(absensi -> (waktuMasuk == null || absensi.getWaktuMasuk().equals(waktuMasuk)))
            .filter(absensi -> (waktuKeluar == null || absensi.getWaktuKeluar().equals(waktuKeluar)))
            .filter(absensi -> (status == null || absensi.getStatusAbsensi().equals(status)))
            .collect(Collectors.toList());

        filteredAbsensis.forEach(absensi -> {
            if(absensi.getStatusAbsensi() == null && absensi.getTanggal().isBefore(LocalDate.now())) {
                absensi.setStatusAbsensi(StatusAbsensi.ALPHA);
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

        if(absensi.getStatusAbsensi().equals(StatusAbsensi.HADIR)) {
            if(existingAbsensi.getWaktuMasuk() == null)
                absensi.setWaktuMasuk(LocalTime.now());
            else if(existingAbsensi.getWaktuKeluar() == null)
                absensi.setWaktuKeluar(LocalTime.now());
        }

        if(absensi.getKaryawan() != null) existingAbsensi.setKaryawan(absensi.getKaryawan());
        if(absensi.getStatusAbsensi() != null) existingAbsensi.setStatusAbsensi(absensi.getStatusAbsensi());
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
