package com.company.models.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tugas")
@Setter
@Getter
@NoArgsConstructor
public class Tugas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "penugasan_id", nullable = false)
    private Penugasan penugasan;
    
    @Column(name = "file_path")
    private String filePath;
    
    @Column(name = "komentar", columnDefinition = "TEXT")
    private String komentar;
    
    @Column(name = "waktu_submit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuSubmit;
    
    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

}
