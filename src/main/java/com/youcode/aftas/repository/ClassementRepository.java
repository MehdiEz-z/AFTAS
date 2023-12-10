package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Classement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassementRepository extends JpaRepository<Classement,Long> {
}
