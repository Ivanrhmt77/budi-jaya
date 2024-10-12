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
import com.company.models.entities.Karyawan;
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
                        @RequestParam(required = false) Karyawan karyawanId,
                        @RequestParam(required = false) String bulan,
                        @RequestParam(required = false) Double gajiPokok,
                        @RequestParam(required = false) Double tunjangan,
                        @RequestParam(required = false) Double potongan,
                        @RequestParam(required = false) Double totalGaji) {
        return gajiService.find(id, karyawanId, bulan, gajiPokok, tunjangan, potongan, totalGaji);
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
