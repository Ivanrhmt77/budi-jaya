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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karyawan_id", nullable = false)
    private Karyawan karyawan;

    @Column(name = "tanggal", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggal;

    @Column(name = "waktu_masuk")
    private LocalTime waktuMasuk;

    @Column(name = "waktu_keluar")
    private LocalTime waktuKeluar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_absensi")
    private StatusAbsensi statusAbsensi;
   
}
