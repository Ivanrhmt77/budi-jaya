package com.company.models.repository;

import org.springframework.data.repository.CrudRepository;
import com.company.models.entities.Absensi;
import java.util.List;
import java.util.Date;
import com.company.models.entities.enums.StatusAbsensi;

public interface AbsensiRepo extends CrudRepository<Absensi, Integer> {
    List<Absensi> findByTanggal(Date tanggal);
    List<Absensi> findByStatus(StatusAbsensi status);
}
