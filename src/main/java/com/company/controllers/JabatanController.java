package com.company.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.company.models.entities.enums.JabatanLevel;
import com.company.services.JabatanService;

@RestController
@RequestMapping("/api/jabatan")
public class JabatanController {
    
    @Autowired
    private JabatanService jabatanService;

    @PostMapping
    public List<Jabatan> create(@RequestBody List<Jabatan> jabatanList) {
        return jabatanService.create(jabatanList);
    }

    @GetMapping
    public Object find(@RequestParam(required = false) Integer id,
                        @RequestParam(required = false) String namaJabatan,
                        @RequestParam(required = false) JabatanLevel level,
                        @RequestParam(required = false) Departemen departemen) {
        return jabatanService.find(id, namaJabatan, level, departemen);
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
