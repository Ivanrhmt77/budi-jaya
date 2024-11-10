package com.company.models.entities;

import java.util.Date;

import com.company.models.entities.enums.StatusTugas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "penugasan")
@Setter
@Getter
@NoArgsConstructor
public class Penugasan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @ManyToOne
    @JoinColumn(name = "karyawan_id", nullable = false)
    private Karyawan karyawan;
    
    @Column(name = "judul_tugas", nullable = false)
    private String judulTugas;
    
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    
    @Column(name = "status_tugas")
    @Enumerated(EnumType.STRING)
    private StatusTugas statusTugas;
    
    @OneToOne
    @JoinColumn(name = "tugas_id", referencedColumnName = "id")
    private Tugas tugas;
    
}
