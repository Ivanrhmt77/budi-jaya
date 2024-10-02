package com.company.models.entities;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import com.company.models.entities.enums.StatusAbsensi;

@Entity
@Table(name = "absensi")
public class Absensi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    @Column(name = "status_absensi", columnDefinition = "ENUM('hadir', 'izin', 'sakit', 'alpha')")
    private StatusAbsensi status;
    
    public Absensi() {
    }

    public Absensi(int id, Karyawan karyawanId, Date tanggal, LocalTime waktuMasuk, LocalTime waktuKeluar, StatusAbsensi status) {
        this.id = id;
        this.karyawanId = karyawanId;
        this.tanggal = tanggal;
        this.waktuMasuk = waktuMasuk;
        this.waktuKeluar = waktuKeluar;
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public Karyawan getKaryawanId() {
        return karyawanId;
    }
    public Date getTanggal() {
        return tanggal;
    }
    public LocalTime getWaktuMasuk() {
        return waktuMasuk;
    }
    public LocalTime getWaktuKeluar() {
        return waktuKeluar;
    }
    public StatusAbsensi getStatus() {
        return status;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setKaryawanId(Karyawan karyawanId) {
        this.karyawanId = karyawanId;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    public void setWaktuMasuk(LocalTime waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }
    public void setWaktuKeluar(LocalTime waktuKeluar) {
        this.waktuKeluar = waktuKeluar;
    }
    public void setStatus(StatusAbsensi status) {
        this.status = status;
    }
}
