package com.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.models.entities.Departemen;
import com.company.models.repository.DepartemenRepo;
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
        return departemenRepo.findById(id).get();
    }

    public Iterable<Departemen> findAll() {
        return departemenRepo.findAll();
    }

    public void removeOne(Integer id) {
        departemenRepo.deleteById(id);
    }
}
