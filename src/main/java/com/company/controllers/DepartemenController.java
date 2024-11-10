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
import com.company.services.DepartemenService;

@RestController
@RequestMapping("/api/departemen")
public class DepartemenController {
    
    @Autowired
    private DepartemenService departemenService;

    @PostMapping
    public List<Departemen> create(@RequestBody List<Departemen> departemenList) {
        return departemenService.create(departemenList);
    }

    @GetMapping
    public Object find(@RequestParam(required = false) Integer id,
                        @RequestParam(required = false) String namaDepartemen) {
        return departemenService.find(id, namaDepartemen);
    }

    @PutMapping("/update")
    public Departemen updateOne(@RequestParam("id") Integer id, @RequestBody Departemen departemen) {
        return departemenService.updateOne(id, departemen);
    }

    @DeleteMapping("/delete")
    public void removeOne(@RequestParam("id") Integer id) {
        departemenService.removeOne(id);
    }
}
