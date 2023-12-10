package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau,Long> {
    boolean existsByCodeNiveau(Integer code);
    Niveau findTopByOrderByCodeNiveauDesc();
    Optional<Niveau> getByCodeNiveau(Integer code);
    void deleteByCodeNiveau(Integer code);
}
