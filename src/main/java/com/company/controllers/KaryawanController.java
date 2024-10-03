package com.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.company.models.entities.Karyawan;
import com.company.services.KaryawanService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/karyawan")
public class KaryawanController {
    
    @Autowired
    private KaryawanService karyawanService;

    @PostMapping
    public Karyawan create(@RequestBody Karyawan karyawan) {
        return karyawanService.save(karyawan);
    }

    @GetMapping
    public Iterable<Karyawan> findAll() {
        return karyawanService.findAll();
    }
    
}
