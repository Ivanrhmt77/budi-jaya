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
import com.company.models.entities.Departemen;
import com.company.services.DepartemenService;

@RestController
@RequestMapping("/api/departemen")
public class DepartemenController {
    
    @Autowired
    private DepartemenService departemenService;

    @PostMapping
    public Departemen create(@RequestBody Departemen departemen) {
        return departemenService.save(departemen);
    }

    @GetMapping
    public Iterable<Departemen> findAll() {
        return departemenService.findAll();
    }

    @GetMapping("/{id}")
    public Departemen findOne(@PathVariable("id") Integer id) {
        return departemenService.findOne(id);
    }

    @PutMapping
    public Departemen update(@RequestBody Departemen departemen) {
        return departemenService.save(departemen);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Integer id) {
        departemenService.removeOne(id);
    }
}
