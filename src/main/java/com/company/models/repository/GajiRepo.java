package com.company.models.repository;

import org.springframework.data.repository.CrudRepository;
import com.company.models.entities.Gaji;
import java.util.List;


public interface GajiRepo extends CrudRepository<Gaji, Integer> {
    List<Gaji> findByBulan(String bulan);
}
