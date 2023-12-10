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
    @Column(unique = true)
    private String nombreAdhesion;
    private String nomMembre;
    private String prenomMembre;
    private LocalDate dateAdhesion;
    private String nationalite;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identiteType;
    @OneToMany(mappedBy = "membre")
    private List<Rank> ranks;
    @OneToMany(mappedBy = "membre")
    private List<Chasse> chasses;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
