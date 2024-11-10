package com.company.models.entities;

import java.util.Date;

import com.company.models.entities.enums.StatusProject;

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
@Table(name = "project")
@Setter
@Getter
@NoArgsConstructor
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nama_project", nullable = false)
    private String namaProject;
    
    @Column(name = "deskripsi", columnDefinition = "TEXT")
    private String deskripsi;
    
    @Column(name = "tanggal_mulai")
    @Temporal(TemporalType.DATE)
    private Date tanggalMulai;
    
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    
    @Column(name = "status_project")
    @Enumerated(EnumType.STRING)
    private StatusProject statusProject;
    
    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Karyawan manager;

    public Project(Integer id) {
        this.id = id;
    }

}
