package com.youcode.aftas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners({AuditingEntityListener.class})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chasse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChasse;
    @ManyToOne
    private Poisson poisson;
    private Integer nombrePoisson;
    @ManyToOne
    private Membre membre;
    @ManyToOne
    private Competition competition;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
