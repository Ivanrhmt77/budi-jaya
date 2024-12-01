package com.company.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.models.entities.Departemen;
import com.company.models.entities.Jabatan;
import com.company.models.entities.Karyawan;
import com.company.models.entities.enums.StatusKaryawan;
import com.company.models.repository.KaryawanRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class KaryawanService {
    
    @Autowired
    private KaryawanRepo karyawanRepo;

    @Autowired
    private JabatanService jabatanService;

    public Karyawan create(Karyawan karyawan) {
        if (karyawanRepo.findByEmail(karyawan.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists: " + karyawan.getEmail());
        }
    
        Integer jabatanId = karyawan.getJabatan().getId();
        if (jabatanId == null) {
            throw new IllegalArgumentException("Jabatan ID is required for Karyawan: " + karyawan.getNamaLengkap());
        }
    
        Jabatan jabatan = jabatanService.findOne(jabatanId);
        karyawan.setJabatan(jabatan);
        karyawan.setDepartemen(jabatan.getDepartemen());
        karyawan.setTanggalMasuk(LocalDate.now());
        karyawan.setFotoProfil("profile.jpg");
    
        return karyawanRepo.save(karyawan);
    }    

    public List<Karyawan> find(Integer id, String namaLengkap, String email, String nomorTelepon, LocalDate tanggalLahir, String alamat, LocalDate tanggalMasuk, Departemen departemen, Jabatan jabatan, StatusKaryawan status) {
        List<Karyawan> karyawans = new ArrayList<>();
        karyawanRepo.findAll().forEach(karyawans::add);

        List<Karyawan> filteredKaryawans = karyawans.stream()
            .filter(karyawan -> (id == null || karyawan.getId().equals(id)))
            .filter(karyawan -> (namaLengkap == null || karyawan.getNamaLengkap().equalsIgnoreCase(namaLengkap)))
            .filter(karyawan -> (email == null || karyawan.getEmail().equals(email)))
            .filter(karyawan -> (nomorTelepon == null || karyawan.getNomorTelepon().equals(nomorTelepon)))
            .filter(karyawan -> (tanggalLahir == null || karyawan.getTanggalLahir().equals(tanggalLahir)))
            .filter(karyawan -> (alamat == null || karyawan.getAlamat().equalsIgnoreCase(alamat)))
            .filter(karyawan -> (tanggalMasuk == null || karyawan.getTanggalMasuk().equals(tanggalMasuk)))
            .filter(karyawan -> (departemen == null || karyawan.getDepartemen().equals(departemen)))
            .filter(karyawan -> (jabatan == null || karyawan.getJabatan().equals(jabatan)))
            .filter(karyawan -> (status == null || karyawan.getStatusKaryawan().equals(status)))
            .collect(Collectors.toList());

        return filteredKaryawans;
    }

    private Karyawan findOne(Integer id) {
        return karyawanRepo.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Karyawan with ID " + id + " not found")
        );
    }

    public Karyawan updateOne(Integer id, Karyawan karyawan) {
        Karyawan existingKaryawan = this.findOne(id);

        if(karyawan.getNamaLengkap() != null) existingKaryawan.setNamaLengkap(karyawan.getNamaLengkap());
        if(karyawan.getEmail() != null) existingKaryawan.setEmail(karyawan.getEmail());
        if(karyawan.getNomorTelepon() != null) existingKaryawan.setNomorTelepon(karyawan.getNomorTelepon());
        if(karyawan.getTanggalLahir() != null) existingKaryawan.setTanggalLahir(karyawan.getTanggalLahir());
        if(karyawan.getAlamat() != null) existingKaryawan.setAlamat(karyawan.getAlamat());
        if(karyawan.getTanggalMasuk() != null) existingKaryawan.setTanggalMasuk(karyawan.getTanggalMasuk());
        if(karyawan.getFotoProfil() != null) existingKaryawan.setFotoProfil(karyawan.getFotoProfil());
        if(karyawan.getDepartemen() != null) existingKaryawan.setDepartemen(karyawan.getDepartemen());
        if(karyawan.getJabatan() != null) existingKaryawan.setJabatan(karyawan.getJabatan());
        if(karyawan.getStatusKaryawan() != null) existingKaryawan.setStatusKaryawan(karyawan.getStatusKaryawan());

        return karyawanRepo.save(existingKaryawan);
    }

    public void removeOne(Integer id) {
        Karyawan existingKaryawan = this.findOne(id);

        karyawanRepo.delete(existingKaryawan);
    }
}
