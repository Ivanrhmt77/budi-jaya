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
import com.company.models.entities.Departemen;
import com.company.services.DepartemenService;

@RestController
@RequestMapping("/api/departemen")
public class DepartemenController {
    
    @Autowired
    private DepartemenService departemenService;

    @PostMapping
    public Departemen create(@RequestBody Departemen departemen) {
        return departemenService.create(departemen);
    }

    @GetMapping
    public Object find(@RequestParam(required = false) Integer id,
                        @RequestParam(required = false) String namaDepartemen) {
        if(id != null) {
            return departemenService.findOne(id);
        } else if(namaDepartemen != null) {
            return departemenService.findByNamaDepartemen(namaDepartemen);
        } else {
            return departemenService.findAll();
        }
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
