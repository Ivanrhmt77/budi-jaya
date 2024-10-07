package com.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.company.models.entities.Absensi;
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
    public Iterable<Absensi> findAll() {
        return absensiService.findAll();
    }

    @GetMapping("/{id}")
    public Absensi findOne(@PathVariable ("id") Integer id) {
        return absensiService.findOne(id);
    }

    @PutMapping("/{id}")
    public Absensi updateOne(@PathVariable("id") Integer id, @RequestBody Absensi absensi) {
        return absensiService.updateOne(id, absensi);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id) {
        absensiService.removeOne(id);
    }
}
