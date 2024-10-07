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

import com.company.models.entities.Gaji;
import com.company.services.GajiService;

@RestController
@RequestMapping("api/gaji")
public class GajiController {
    
    @Autowired
    private GajiService gajiService;

    @PostMapping
    public Gaji create(@RequestBody Gaji gaji) {
        return gajiService.create(gaji);
    }

    @GetMapping
    public Iterable<Gaji> findAll() {
        return gajiService.findAll();
    }

    @GetMapping("/{id}")
    public Gaji findOne(@PathVariable("id") Integer id) {
        return gajiService.findOne(id);
    }

    @PutMapping("/{id}")
    public Gaji updateOne(@PathVariable("id") Integer id, @RequestBody Gaji gaji) {
        return gajiService.updateOne(id, gaji);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id) {
        gajiService.removeOne(id);
    }
}
