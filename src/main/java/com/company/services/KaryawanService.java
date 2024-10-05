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

    public Karyawan create(Karyawan karyawan) {
        if (karyawanRepo.findByEmail(karyawan.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists: " + karyawan.getEmail());
        }
        return karyawanRepo.save(karyawan);
    }

    public Karyawan updateOne(Integer id, Karyawan karyawan) {
        Karyawan existingKaryawan = karyawanRepo.findById(id).get();

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
