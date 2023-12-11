package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition,Long> {
    boolean existsByDateCompetition(LocalDate dateCompetition);
    Optional<Competition> findByCodeCompetition(String codeCompetition);
}
