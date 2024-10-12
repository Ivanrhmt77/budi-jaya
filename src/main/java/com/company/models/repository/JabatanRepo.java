package com.company.models.repository;

import org.springframework.data.repository.CrudRepository;
import com.company.models.entities.Jabatan;
import java.util.List;


public interface JabatanRepo extends CrudRepository<Jabatan, Integer> {
    List<Jabatan> findByNamaJabatan(String namaJabatan);
}
