package com.company.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.company.models.entities.enums.StatusKaryawan;

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
@Table(name = "karyawan")
@Setter
@Getter
@NoArgsConstructor
public class Karyawan implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_lengkap", nullable = false, length = 100)
    private String namaLengkap;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "nomor_telepon", length = 15)
    private String nomorTelepon;

    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDate tanggalLahir;

    @Column(name = "alamat", columnDefinition = "TEXT")
    private String alamat;

    @Column(name = "tanggal_masuk", nullable = false)
    private LocalDate tanggalMasuk;

    @Column(name = "foto_profil", length = 255)
    private String fotoProfil;

    @ManyToOne
    @JoinColumn(name = "departemen_id")
    private Departemen departemen;

    @ManyToOne
    @JoinColumn(name = "jabatan_id")
    private Jabatan jabatan;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_karyawan")
    private StatusKaryawan statusKaryawan;

    public Karyawan(Integer id) {
        this.id = id;
    }
        
    public Karyawan(String email) {
        this.email = email;
    }    
}
