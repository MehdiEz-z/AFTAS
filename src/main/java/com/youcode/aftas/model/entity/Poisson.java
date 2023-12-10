package com.youcode.aftas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners({AuditingEntityListener.class})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Poisson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoisson;
    @Column(unique = true)
    private String nomPoisson;
    private Double poidsMoyen;
    @OneToMany(mappedBy = "poisson")
    private List<Chasse> chasses;
    @ManyToOne
    private Niveau niveau;
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}