package com.youcode.aftas.controller.peche.vm.request;

import com.youcode.aftas.model.entity.Competition;
import com.youcode.aftas.model.entity.Membre;
import com.youcode.aftas.model.entity.Peche;
import com.youcode.aftas.model.entity.Poisson;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PecheRequestVM(
        @NotBlank(message = "Le code de competition est Obligatoire")
        String competitionCode,
        @NotBlank(message = "Le code de membre est Obligatoire")
        String membreCode,
        @NotBlank(message = "Le code de competition est Obligatoire")
        String nomPoisson,
        @NotNull(message = "Le Poids du poisson est Obligatoire")
        @Digits(integer = 10, fraction = 2, message = "Le Poids du poisson doit être une valeur numérique avec jusqu'à 2 décimales")
        @Positive(message = "Le Poids du poisson doit être supérieur a 0")
        Double poidsPoisson
)
{
        public Peche toEntite(){
                return Peche.builder()
                        .competition(Competition.builder()
                                .codeCompetition(competitionCode)
                                .build())
                        .membre(Membre.builder()
                                .numeroAdhesion(membreCode)
                                .build())
                        .poisson(Poisson.builder()
                                .nomPoisson(nomPoisson)
                                .build())
                        .build();
        }
}
