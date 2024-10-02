package com.company.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "jabatan")
public class Jabatan {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "nama_jabatan", nullable = false, length = 100)
    private String namaJabatan;

    public Jabatan() {
    }

    public Jabatan(int id, String namaJabatan) {
        this.id = id;
        this.namaJabatan = namaJabatan;
    }

    public int getId() {
        return id;
    }

    public String getnamaJabatan() {
        return namaJabatan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setnamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }
}
