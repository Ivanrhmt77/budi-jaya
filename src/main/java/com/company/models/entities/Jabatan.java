package com.company.models.entities;

import com.company.models.entities.enums.JabatanLevel;
import com.company.models.entities.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private JabatanLevel level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departemen_id", nullable = false)
    private Departemen departemen;

    public UserRole getUserRole() {
        switch (this.level) {
            case DIRECTOR:
            case PRESIDENT:
                return UserRole.ADMIN;
            case MANAGER:
                return UserRole.MANAGER;
            default:
                return UserRole.KARYAWAN;
        }
    }

}
