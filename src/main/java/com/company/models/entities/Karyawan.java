package com.company.models.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import com.company.models.entities.enums.StatusKaryawan;

@Entity
@Table(name = "karyawan")
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
    @Column(name = "status")
    private StatusKaryawan status;

    public Karyawan() {
    }

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

    public Integer getId() {
        return id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public Departemen getDepartemen() {
        return departemen;
    }

    public Jabatan getJabatan() {
        return jabatan;
    }

    public StatusKaryawan getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public void setDepartemen(Departemen departemen) {
        this.departemen = departemen;
    }

    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

    public void setStatus(StatusKaryawan status) {
        this.status = status;
    }
}
