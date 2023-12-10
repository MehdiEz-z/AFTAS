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
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNiveau;
    @Column(unique = true)
    private Integer codeNiveau;
    private String descriptionNiveau;
    private int points;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
