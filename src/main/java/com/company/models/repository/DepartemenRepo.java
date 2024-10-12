package com.company.models.repository;

import org.springframework.data.repository.CrudRepository;
import com.company.models.entities.Departemen;
import java.util.List;


public interface DepartemenRepo extends CrudRepository<Departemen, Integer> {
    List<Departemen> findByNamaDepartemen(String namaDepartemen);
}
