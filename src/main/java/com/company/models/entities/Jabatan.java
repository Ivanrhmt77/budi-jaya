package com.company.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jabatan")
@Setter
@Getter
@NoArgsConstructor
public class Jabatan {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_jabatan", nullable = false, length = 100)
    private String namaJabatan;

    public Jabatan(Integer id, String namaJabatan) {
        this.id = id;
        this.namaJabatan = namaJabatan;
    }

}
