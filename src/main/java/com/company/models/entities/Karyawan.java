package com.company.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import com.company.models.entities.enums.StatusKaryawan;

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

    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;

    @Column(name = "alamat", columnDefinition = "TEXT")
    private String alamat;

    @Column(name = "tanggal_masuk")
    @Temporal(TemporalType.DATE)
    private Date tanggalMasuk;

    @ManyToOne
    @JoinColumn(name = "departemen_id", nullable = false)
    private Departemen departemen;

    @ManyToOne
    @JoinColumn(name = "jabatan_id", nullable = false)
    private Jabatan jabatan;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('AKTIF', 'NONAKTIF')")
    private StatusKaryawan status;

    public Karyawan(Integer id, String namaLengkap, String email, String nomorTelepon, Date tanggalLahir, String alamat,
            Date tanggalMasuk, Departemen departemen, Jabatan jabatan, StatusKaryawan status) {
        this.id = id;
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.nomorTelepon = nomorTelepon;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.tanggalMasuk = tanggalMasuk;
        this.departemen = departemen;
        this.jabatan = jabatan;
        this.status = status;
    }
    
}
