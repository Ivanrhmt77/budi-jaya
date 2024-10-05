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
    public Iterable<Jabatan> findAll() {
        return jabatanService.findAll();
    }

    @GetMapping("/{id}")
    public Jabatan findOne(@PathVariable("id") Integer id) {
        return jabatanService.findOne(id);
    }

    @PutMapping("/{id}")
    public Jabatan updateOne(@PathVariable("id") Integer id, @RequestBody Jabatan jabatan) {
        return jabatanService.updateOne(id, jabatan);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id) {
        jabatanService.removeOne(id);
    }
}
