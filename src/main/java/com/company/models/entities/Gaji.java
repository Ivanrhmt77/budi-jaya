package com.company.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "gaji")
public class Gaji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "karyawan_id", nullable = false)
    private Karyawan karyawanId;

    @Column(name = "bulan", nullable = false, length = 10)
    private String bulan;

    @Column(name = "gaji_pokok", nullable = false)
    private Double gajiPokok;

    @Column(name = "tunjangan", nullable = false)
    private Double tunjangan;

    @Column(name = "potongan", nullable = false)
    private Double potongan;

    @Column(name = "total_gaji", nullable = false)
    private Double totalGaji;
    
    public Gaji() {
    }

    public Gaji(Integer id, Karyawan karyawanId, String bulan, Double gajiPokok, Double tunjangan, Double potongan,
            Double totalGaji) {
        this.id = id;
        this.karyawanId = karyawanId;
        this.bulan = bulan;
        this.gajiPokok = gajiPokok;
        this.tunjangan = tunjangan;
        this.potongan = potongan;
        this.totalGaji = totalGaji;
    }
    public Integer getId() {
        return id;
    }
    public Karyawan getKaryawanId() {
        return karyawanId;
    }
    public String getBulan() {
        return bulan;
    }
    public Double getGajiPokok() {
        return gajiPokok;
    }
    public Double getTunjangan() {
        return tunjangan;
    }
    public Double getPotongan() {
        return potongan;
    }
    public Double getTotalGaji() {
        return totalGaji;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setKaryawanId(Karyawan karyawanId) {
        this.karyawanId = karyawanId;
    }
    public void setBulan(String bulan) {
        this.bulan = bulan;
    }
    public void setGajiPokok(Double gajiPokok) {
        this.gajiPokok = gajiPokok;
    }
    public void setTunjangan(Double tunjangan) {
        this.tunjangan = tunjangan;
    }
    public void setPotongan(Double potongan) {
        this.potongan = potongan;
    }
    public void setTotalGaji(Double totalGaji) {
        this.totalGaji = totalGaji;
    }

}
