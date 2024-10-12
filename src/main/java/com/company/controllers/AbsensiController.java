package com.company.controllers;

import java.time.LocalTime;
import java.util.Date;

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

import com.company.models.entities.Absensi;
import com.company.models.entities.Karyawan;
import com.company.models.entities.enums.StatusAbsensi;
import com.company.services.AbsensiService;

@RestController
@RequestMapping("api/absensi")
public class AbsensiController {
    
    @Autowired
    private AbsensiService absensiService;

    @PostMapping
    public Absensi create(@RequestBody Absensi absensi) {
        return absensiService.create(absensi);
    }

    @GetMapping
    public Object find(@RequestParam(required = false) Integer id,
                        @RequestParam(required = false) Karyawan karyawanId,
                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date tanggal,
                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalTime waktuMasuk,
                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalTime waktuKeluar,
                        @RequestParam(required = false) StatusAbsensi status) {
        return absensiService.find(id, karyawanId, tanggal, waktuMasuk, waktuKeluar, status);
    }

    @PutMapping("/update")
    public Absensi updateOne(@RequestParam("id") Integer id, @RequestBody Absensi absensi) {
        return absensiService.updateOne(id, absensi);
    }

    @DeleteMapping("/delete")
    public void removeOne(@RequestParam("id") Integer id) {
        absensiService.removeOne(id);
    }
}
