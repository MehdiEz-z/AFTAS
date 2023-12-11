package com.youcode.aftas.controller.poisson.vm.request;

import com.youcode.aftas.model.entity.Niveau;
import com.youcode.aftas.model.entity.Poisson;
import jakarta.validation.constraints.*;

public record PoissonRequestVM(
        @NotBlank(message = "Le Nom est Obligatoire")
        @Size(min = 4, max = 15, message = "Le nom doit avoir entre 4 et 15 caractères")
        String nom,
        @NotNull(message = "Le Code est Obligatoire")
        Integer niveau,
        @NotNull(message = "Le Poids moyen du poisson est obligatoire")
        @Digits(integer = 10, fraction = 2, message = "Le Poids doit être une valeur numérique avec jusqu'à 2 décimales")
        @Positive(message = "Le Poids doit être supérieur a 0")
        Double poidsMoyen
) {
    public Poisson toEntite(){
        return Poisson.builder()
                .nomPoisson(nom)
                .niveau(Niveau.builder()
                        .codeNiveau(niveau)
                        .build())
                .build();
    }
}
