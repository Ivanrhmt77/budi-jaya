package com.company.models.entities;

import java.io.Serializable;
import java.util.Date;

import com.company.models.entities.enums.JabatanLevel;
import com.company.models.entities.enums.StatusKaryawan;
import com.company.models.entities.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "karyawan")
@Setter
@Getter
@NoArgsConstructor
public class Karyawan implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_lengkap", nullable = false, length = 100)
    private String namaLengkap;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "nomor_telepon", length = 15)
    private String nomorTelepon;

    @Column(name = "tanggal_lahir", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;

    @Column(name = "alamat", columnDefinition = "TEXT")
    private String alamat;

    @Column(name = "tanggal_masuk", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalMasuk;

    @Column(name = "foto_profil", length = 255)
    private String fotoProfil;

    @ManyToOne
    @JoinColumn(name = "departemen_id")
    private Departemen departemen;

    @ManyToOne
    @JoinColumn(name = "jabatan_id", nullable = false)
    private Jabatan jabatan;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_karyawan")
    private StatusKaryawan statusKaryawan;

    public Karyawan(Integer id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return this.jabatan != null && 
               (this.jabatan.getLevel() == JabatanLevel.DIRECTOR || 
                this.jabatan.getLevel() == JabatanLevel.PRESIDENT);
    }
    
    public boolean isManager() {
        return this.jabatan != null && 
                this.jabatan.getLevel() == JabatanLevel.MANAGER;
    }
    
    public boolean isStaff() {
        return this.jabatan != null && 
               this.jabatan.getLevel() == JabatanLevel.STAFF;
    }
    
    public UserRole getRole() {
        if (isAdmin()) return UserRole.ADMIN;
        if (isManager()) return UserRole.MANAGER;
        return UserRole.KARYAWAN;
    }
    
}
