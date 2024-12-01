package com.company.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.models.entities.Departemen;
import com.company.models.entities.Jabatan;
import com.company.models.entities.Karyawan;
import com.company.models.entities.enums.StatusKaryawan;
import com.company.services.KaryawanService;

@RestController
@RequestMapping("/api/karyawan")
public class KaryawanController {
    
    @Autowired
    private KaryawanService karyawanService;

    @PostMapping
    public Karyawan create(@RequestBody Karyawan karyawan) {
        return karyawanService.create(karyawan);
    }

    @GetMapping
    public Object find(@RequestParam(required = false) Integer id,
                        @RequestParam(required = false) String namaLengkap,
                        @RequestParam(required = false) String email,
                        @RequestParam(required = false) String nomorTelepon,
                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tanggalLahir,
                        @RequestParam(required = false) String alamat,
                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tanggalMasuk,
                        @RequestParam(required = false) Departemen departemen,
                        @RequestParam(required = false) Jabatan jabatan,
                        @RequestParam(required = false) StatusKaryawan status) {
        return karyawanService.find(id, namaLengkap, email, nomorTelepon, tanggalLahir, alamat, tanggalMasuk, departemen, jabatan, status);
    }

    @PutMapping("/update")
    public Karyawan updateOne(@RequestParam("id") Integer id, @RequestBody Karyawan karyawan) {
        return karyawanService.updateOne(id, karyawan);
    }

    @DeleteMapping("/delete")
    public void removeOne(@RequestParam("id") Integer id) {
        karyawanService.removeOne(id);
    }
}
