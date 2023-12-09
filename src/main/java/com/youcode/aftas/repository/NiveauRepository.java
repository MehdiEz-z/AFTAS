package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau,Long> {
}
