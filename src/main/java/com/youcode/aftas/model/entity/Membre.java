package com.youcode.aftas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.aftas.model.enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners({AuditingEntityListener.class})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMembre;
    @Column(name = "numero_adhesion", unique = true)
    private String numeroAdhesion;
    @Column(name = "nom_membre")
    private String nomMembre;
    @Column(name = "prenom_membre")
    private String prenomMembre;
    @Column(name = "date_naissance_membre")
    private LocalDate dateNaissanceMembre;
    @Column(name = "nationalite_membre")
    private String nationaliteMembre;
    @Enumerated(EnumType.STRING)
    @Column(name = "identite_type")
    private IdentityDocumentType identiteType;
    @OneToMany(mappedBy = "membre")
    private List<Rank> ranks;
    @OneToMany(mappedBy = "membre")
    private List<Chasse> chasses;
    @JsonIgnore
    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
