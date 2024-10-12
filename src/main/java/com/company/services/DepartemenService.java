package com.company.services;

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

    public Departemen updateOne(Integer id, Departemen departemen) {
        Departemen existingDepartemen = this.findOne(id);

        existingDepartemen.setnamaDepartemen(departemen.getnamaDepartemen());

        return departemenRepo.save(existingDepartemen);
    }

    public Departemen findOne(Integer id) {
        return departemenRepo.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Absensi with ID " + id + " not found")
        );
    }

    public Iterable<Departemen> findAll() {
        return departemenRepo.findAll();
    }

    public Iterable<Departemen> findByNamaDepartemen(String namaDepartemen) {
        return departemenRepo.findByNamaDepartemen(namaDepartemen);
    }

    public void removeOne(Integer id) {
        if(!departemenRepo.existsById(id)) {
            throw new EntityNotFoundException("Absensi with ID " + id + " not found");
        }

        departemenRepo.deleteById(id);
    }
}
