package com.company.models.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.company.models.entities.enums.StatusAbsensi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Karyawan karyawan;

    @Column(name = "tanggal", nullable = false)
    private LocalDate tanggal;

    @Column(name = "waktu_masuk")
    private LocalTime waktuMasuk;

    @Column(name = "waktu_keluar")
    private LocalTime waktuKeluar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_absensi")
    private StatusAbsensi statusAbsensi;
   
}
