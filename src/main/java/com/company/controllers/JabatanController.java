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
import com.company.models.entities.Jabatan;
import com.company.services.JabatanService;

@RestController
@RequestMapping("/api/jabatan")
public class JabatanController {
    
    @Autowired
    private JabatanService jabatanService;

    @PostMapping
    public Jabatan create(@RequestBody Jabatan jabatan) {
        return jabatanService.create(jabatan);
    }

    @GetMapping
    public Object find(@RequestParam(required = false) Integer id,
                        @RequestParam(required = false) String namaJabatan) {
        if(id != null) {
            return jabatanService.findOne(id);
        } else if(namaJabatan != null) {
            return jabatanService.findByNamaJabatan(namaJabatan);
        } else {
            return jabatanService.findAll();
        }
    }

    @PutMapping("/update")
    public Jabatan updateOne(@RequestParam("id") Integer id, @RequestBody Jabatan jabatan) {
        return jabatanService.updateOne(id, jabatan);
    }

    @DeleteMapping("/delete")
    public void removeOne(@RequestParam("id") Integer id) {
        jabatanService.removeOne(id);
    }
}
