package com.youcode.aftas.controller.competition.vm.request;

import com.youcode.aftas.model.entity.Classement;
import com.youcode.aftas.model.entity.Competition;
import com.youcode.aftas.model.entity.Membre;
import jakarta.validation.constraints.NotBlank;

public record InscriptionRequestVM(
        @NotBlank(message = "Le code de competition est Obligatoire")
        String competitionCode,
        @NotBlank(message = "Le code de membre est Obligatoire")
        String membreCode
) {
    public Classement toEntite(){
        return Classement.builder()
                .competition(Competition.builder()
                        .codeCompetition(competitionCode)
                        .build()
                )
                .membre(Membre.builder()
                        .numeroAdhesion(membreCode)
                        .build())
                .build();
    }
}
