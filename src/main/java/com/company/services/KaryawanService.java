package com.company.services;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
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

    public Karyawan create(Karyawan karyawan) {
        if (karyawanRepo.findByEmail(karyawan.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists: " + karyawan.getEmail());
        }
        return karyawanRepo.save(karyawan);
    }

    public List<Karyawan> find(Integer id, String namaLengkap, String email, String nomorTelepon, Date tanggalLahir, String alamat, Date tanggalMasuk, Departemen departemen, Jabatan jabatan, StatusKaryawan status) {
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
            .filter(karyawan -> (status == null || karyawan.getStatus().equals(status)))
            .collect(Collectors.toList());
        
        if(filteredKaryawans.isEmpty()) {
            throw new EntityNotFoundException("Karyawan not found");
        }

        return filteredKaryawans;
    }

    private Karyawan findOne(Integer id) {
        return karyawanRepo.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Karyawan with ID " + id + " not found")
        );
    }

    public Karyawan updateOne(Integer id, Karyawan karyawan) {
        Karyawan existingKaryawan = this.findOne(id);

        existingKaryawan.setNamaLengkap(karyawan.getNamaLengkap());
        existingKaryawan.setEmail(karyawan.getEmail());
        existingKaryawan.setNomorTelepon(karyawan.getNomorTelepon());
        existingKaryawan.setTanggalLahir(karyawan.getTanggalLahir());
        existingKaryawan.setAlamat(karyawan.getAlamat());
        existingKaryawan.setTanggalMasuk(karyawan.getTanggalMasuk());
        existingKaryawan.setDepartemen(karyawan.getDepartemen());
        existingKaryawan.setJabatan(karyawan.getJabatan());
        existingKaryawan.setStatus(karyawan.getStatus());

        return karyawanRepo.save(existingKaryawan);
    }

    public void removeOne(Integer id) {
        if(karyawanRepo.existsById(id)) {
            throw new EntityNotFoundException("Absensi with ID " + id + " not found");
        }

        karyawanRepo.deleteById(id);
    }
}
