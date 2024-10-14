package com.company.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "departemen")
public class Departemen {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_departemen", nullable = false, length = 100)
    private String namaDepartemen;

    public Departemen() {
    }

    public Departemen(Integer id, String namaDepartemen) {
        this.id = id;
        this.namaDepartemen = namaDepartemen;
    }

    public Integer getId() {
        return id;
    }

    public String getNamaDepartemen() {
        return namaDepartemen;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNamaDepartemen(String namaDepartemen) {
        this.namaDepartemen = namaDepartemen;
    }
}
