package com.company.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.models.entities.Departemen;
import com.company.models.repository.DepartemenRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartemenService {
    
    @Autowired
    private DepartemenRepo departemenRepo;

    public Departemen create(Departemen departemen) {
        return departemenRepo.save(departemen);
    }

    public List<Departemen> find(Integer id, String namaDepartemen) {
        List<Departemen> departemens = new ArrayList<>();
        departemenRepo.findAll().forEach(departemens::add);

        List<Departemen> filteredDepartemens = departemens.stream()
            .filter(departemen -> (id == null || departemen.getId().equals(id)))
            .filter(departemen -> (namaDepartemen == null || departemen.getnamaDepartemen().equalsIgnoreCase(namaDepartemen)))
            .collect(Collectors.toList());

        if(filteredDepartemens.isEmpty()) {
            throw new EntityNotFoundException("Departemen not found");
        }

        return filteredDepartemens;
    }

    private Departemen findOne(Integer id) {
        return departemenRepo.findById(id).orElseThrow(() -> 
            new EntityNotFoundException("Departemen with ID " + id + " not found")
        );
    }

    public Departemen updateOne(Integer id, Departemen departemen) {
        Departemen existingDepartemen = this.findOne(id);

        existingDepartemen.setnamaDepartemen(departemen.getnamaDepartemen());

        return departemenRepo.save(existingDepartemen);
    }

    public void removeOne(Integer id) {
        Departemen existingDepartemen = this.findOne(id);

        departemenRepo.delete(existingDepartemen);
    }
}
