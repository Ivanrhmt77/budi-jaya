package com.company.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gaji")
@Setter
@Getter
@NoArgsConstructor
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

}
