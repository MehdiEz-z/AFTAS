package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Chasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChasseRepository extends JpaRepository<Chasse,Long> {
}
