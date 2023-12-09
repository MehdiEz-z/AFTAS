package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Poisson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoissonRepository extends JpaRepository<Poisson,Long> {
}
