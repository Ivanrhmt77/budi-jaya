package com.company.models.entities;

import com.company.models.entities.enums.JabatanLevel;
import com.company.models.entities.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "karyawan_id")
    private Karyawan karyawan;

    public void setRoleBerdasarkanJabatan() {
        if (this.karyawan != null && this.karyawan.getJabatan() != null) {
            JabatanLevel level = this.karyawan.getJabatan().getLevel();
            switch (level) {
                case DIRECTOR:
                case PRESIDENT:
                    this.role = UserRole.ADMIN;
                    break;
                case MANAGER:
                    this.role = UserRole.MANAGER;
                    break;
                case STAFF:
                default:
                    this.role = UserRole.KARYAWAN;
                    break;
            }
        }
    }

}
