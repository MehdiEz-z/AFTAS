package com.youcode.aftas.repository;

import com.youcode.aftas.model.entity.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre,Long> {
    boolean existsByNumeroAdhesion(String nombreAdhesion);
    Optional<Membre> findByNumeroAdhesion(String nombreAdhesion);
    List<Membre>findByNumeroAdhesionStartingWithIgnoreCaseOrNomMembreStartingWithIgnoreCaseOrPrenomMembreStartingWithIgnoreCase(String nombreAdhesion, String nomMembre, String prenomMembre);

    /*@Query("SELECT m FROM Membre m WHERE " +
            "LOWER(m.nombreAdhesion) LIKE LOWER(CONCAT('%', :nombreAdhesion, '%')) OR " +
            "LOWER(m.nomMembre) LIKE LOWER(CONCAT('%', :nomMembre, '%')) OR " +
            "LOWER(m.prenomMembre) LIKE LOWER(CONCAT('%', :prenomMembre, '%'))")
    List<Membre> findByCriteria(@Param("nombreAdhesion") String nombreAdhesion,
                                @Param("nomMembre") String nomMembre,
                                @Param("prenomMembre") String prenomMembre);*/
    void deleteByNumeroAdhesion(String numeroAdhesion);
}
