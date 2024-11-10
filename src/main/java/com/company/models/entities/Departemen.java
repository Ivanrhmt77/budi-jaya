package com.company.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    public Departemen(Integer id) {
        this.id = id;
    }

}
