package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Poisson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoissonRepository extends JpaRepository<Poisson,Long> {
    boolean existsByNomPoisson(String poisson);
    Optional<Poisson> findByNomPoisson(String poisson);
    void deleteByNomPoisson(String poisson);
}
