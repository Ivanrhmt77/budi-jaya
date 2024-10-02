package com.company.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "gaji")
public class Gaji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "karyawan_id", nullable = false)
    private Karyawan karyawanId;

    @Column(name = "bulan", nullable = false, length = 10)
    private String bulan;

    @Column(name = "gaji_pokok", nullable = false)
    private double gajiPokok;

    @Column(name = "tunjangan", nullable = false)
    private double tunjangan;

    @Column(name = "potongan", nullable = false)
    private double potongan;

    @Column(name = "total_gaji", nullable = false)
    private double totalGaji;
    
    public Gaji() {
    }

    public Gaji(int id, Karyawan karyawanId, String bulan, double gajiPokok, double tunjangan, double potongan,
            double totalGaji) {
        this.id = id;
        this.karyawanId = karyawanId;
        this.bulan = bulan;
        this.gajiPokok = gajiPokok;
        this.tunjangan = tunjangan;
        this.potongan = potongan;
        this.totalGaji = totalGaji;
    }
    public int getId() {
        return id;
    }
    public Karyawan getKaryawanId() {
        return karyawanId;
    }
    public String getBulan() {
        return bulan;
    }
    public double getGajiPokok() {
        return gajiPokok;
    }
    public double getTunjangan() {
        return tunjangan;
    }
    public double getPotongan() {
        return potongan;
    }
    public double getTotalGaji() {
        return totalGaji;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setKaryawanId(Karyawan karyawanId) {
        this.karyawanId = karyawanId;
    }
    public void setBulan(String bulan) {
        this.bulan = bulan;
    }
    public void setGajiPokok(double gajiPokok) {
        this.gajiPokok = gajiPokok;
    }
    public void setTunjangan(double tunjangan) {
        this.tunjangan = tunjangan;
    }
    public void setPotongan(double potongan) {
        this.potongan = potongan;
    }
    public void setTotalGaji(double totalGaji) {
        this.totalGaji = totalGaji;
    }

}
