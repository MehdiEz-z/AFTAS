package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank,Long> {
}
