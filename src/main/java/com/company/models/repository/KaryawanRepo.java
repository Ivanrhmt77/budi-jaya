package com.company.models.repository;

import org.springframework.data.repository.CrudRepository;
import com.company.models.entities.Departemen;
import com.company.models.entities.Jabatan;
import com.company.models.entities.Karyawan;
import java.util.List;
import com.company.models.entities.enums.StatusKaryawan;



public interface KaryawanRepo extends CrudRepository<Karyawan, Integer> {
    List<Karyawan> findByEmail(String email);
    List<Karyawan> findByNamaLengkap(String namaLengkap);
    List<Karyawan> findByDepartemen(Departemen departemen);
    List<Karyawan> findByJabatan(Jabatan jabatan);
    List<Karyawan> findByStatus(StatusKaryawan status);
}
