package com.youcode.aftas.controller.competition.vm.response;

import com.youcode.aftas.model.entity.Classement;

public record InscriptionResponseVM(
    String competitionCode,
    String membreCode,
    String membreNom,
    int score
) {
    public static InscriptionResponseVM toVM(Classement classement){
        return new InscriptionResponseVM(
                classement.getCompetition().getCodeCompetition(),
                classement.getMembre().getNumeroAdhesion(),
                classement.getMembre().getNomMembre(),
                classement.getScore()
        );
    }
}
