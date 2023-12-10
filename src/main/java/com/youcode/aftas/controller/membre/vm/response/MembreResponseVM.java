package com.youcode.aftas.controller.membre.vm.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youcode.aftas.model.entity.Membre;

import java.time.LocalDate;

public record MembreResponseVM(
        String numero,
        String nom,
        String prenom,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dateNaissance,
        String nationalite,
        String identite
) {
    public static MembreResponseVM toVM(Membre membre){
        return new MembreResponseVM(
            membre.getNumeroAdhesion(),
            membre.getNomMembre(),
            membre.getPrenomMembre(),
            membre.getDateNaissanceMembre(),
            membre.getNationaliteMembre(),
            membre.getIdentiteType().name()
        );
    }
}
