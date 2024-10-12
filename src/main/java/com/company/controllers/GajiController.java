package com.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Object find(@RequestParam(required = false) Integer id,
                        @RequestParam(required = false) String bulan) {
        if(id != null) {
            return gajiService.findOne(id);
        } else if(bulan != null) {
            return gajiService.findByBulan(bulan);
        } else {
            return gajiService.findAll();
        }
    }

    @PutMapping("/update")
    public Gaji updateOne(@RequestParam("id") Integer id, @RequestBody Gaji gaji) {
        return gajiService.updateOne(id, gaji);
    }

    @DeleteMapping("/delete")
    public void removeOne(@RequestParam("id") Integer id) {
        gajiService.removeOne(id);
    }
}
