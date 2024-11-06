package com.company.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
import com.company.models.entities.enums.StatusAbsensi;

@Entity
@Table(name = "absensi")
@Setter
@Getter
@NoArgsConstructor
public class Absensi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "karyawan_id", nullable = false)
    private Karyawan karyawanId;

    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;

    @Column(name = "waktu_masuk")
    private LocalTime waktuMasuk;

    @Column(name = "waktu_keluar")
    private LocalTime waktuKeluar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_absensi", columnDefinition = "ENUM('HADIR', 'IZIN', 'SAKIT', 'ALPHA')")
    private StatusAbsensi status;
    
    public Absensi(Integer id, Karyawan karyawanId, Date tanggal, LocalTime waktuMasuk, LocalTime waktuKeluar, StatusAbsensi status) {
        this.id = id;
        this.karyawanId = karyawanId;
        this.tanggal = tanggal;
        this.waktuMasuk = waktuMasuk;
        this.waktuKeluar = waktuKeluar;
        this.status = status;
    }
   
}
