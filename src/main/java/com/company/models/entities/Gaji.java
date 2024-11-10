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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "karyawan_id", nullable = false)
    private Karyawan karyawan;

    @Column(name = "bulan", nullable = false, length = 10)
    private String bulan;

    @Column(name = "tahun", nullable = false)
    private Integer tahun;

    @Column(name = "gaji_pokok", nullable = false)
    private Double gajiPokok;

    @Column(name = "tunjangan", nullable = false)
    private Double tunjangan;

    @Column(name = "potongan", nullable = false)
    private Double potongan;

    @Column(name = "total_gaji", nullable = false)
    private Double totalGaji;
    
}
