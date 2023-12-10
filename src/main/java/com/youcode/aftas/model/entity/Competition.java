package com.youcode.aftas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.aftas.model.enums.StatutCompetition;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@EntityListeners({AuditingEntityListener.class})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompetition;
    @Column(unique = true)
    private String codeCompetition;
    private LocalDate dateCompetition;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private int nombreParticipants;
    private String location;
    private double prixParticipation;
    @Enumerated(EnumType.STRING)
    private StatutCompetition statutCompetition;
    @OneToMany(mappedBy = "competition")
    private List<Classement> classements;
    @OneToMany(mappedBy = "competition")
    private List<Chasse> chasses;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
