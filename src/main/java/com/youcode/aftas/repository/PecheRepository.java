package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Competition;
import com.youcode.aftas.model.entity.Membre;
import com.youcode.aftas.model.entity.Peche;
import com.youcode.aftas.model.entity.Poisson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PecheRepository extends JpaRepository<Peche,Long> {
    Peche findByPoissonAndMembreAndCompetition(Poisson poisson, Membre membre, Competition competition);
    List<Peche> findByMembreAndCompetition(Membre membre, Competition competition);
}
