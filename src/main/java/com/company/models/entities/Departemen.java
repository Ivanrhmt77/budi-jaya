package com.company.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "departemen")
public class Departemen {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "nama_departemen", nullable = false, length = 100)
    private String namaDepartemen;

    public Departemen() {
    }

    public Departemen(int id, String namaDepartemen) {
        this.id = id;
        this.namaDepartemen = namaDepartemen;
    }

    public int getId() {
        return id;
    }

    public String getnamaDepartemen() {
        return namaDepartemen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setnamaDepartemen(String namaDepartemen) {
        this.namaDepartemen = namaDepartemen;
    }
}
