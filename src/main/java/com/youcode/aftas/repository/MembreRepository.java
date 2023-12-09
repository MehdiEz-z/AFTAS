package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreRepository extends JpaRepository<Membre,Long> {
}
