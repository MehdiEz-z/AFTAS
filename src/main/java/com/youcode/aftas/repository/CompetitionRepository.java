package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition,Long> {
}
