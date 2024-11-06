package com.company.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departemen")
@Setter
@Getter
@NoArgsConstructor
public class Departemen {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_departemen", nullable = false, length = 100)
    private String namaDepartemen;

    public Departemen(Integer id, String namaDepartemen) {
        this.id = id;
        this.namaDepartemen = namaDepartemen;
    }

}
