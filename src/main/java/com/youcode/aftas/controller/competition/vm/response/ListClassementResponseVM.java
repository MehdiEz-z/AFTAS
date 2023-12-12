package com.youcode.aftas.controller.competition.vm.response;

import com.youcode.aftas.model.entity.Classement;

public record ListClassementResponseVM(
    String membreCode,
    String membreNom,
    int score,
    int rank
) {
    public static ListClassementResponseVM toVM(Classement classement){
        return new ListClassementResponseVM(
                classement.getMembre().getNumeroAdhesion(),
                classement.getMembre().getNomMembre(),
                classement.getScore(),
                classement.getClassementMembre()
        );
    }
}
