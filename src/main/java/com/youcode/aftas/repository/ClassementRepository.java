package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Classement;
import com.youcode.aftas.model.entity.Competition;
import com.youcode.aftas.model.entity.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassementRepository extends JpaRepository<Classement,Long> {
    boolean existsByMembreAndCompetition(Membre membre, Competition competition);
    Classement findByMembreAndCompetition(Membre membre, Competition competition);
    List<Classement> findByCompetition_CodeCompetition(String competitionCode);
}
