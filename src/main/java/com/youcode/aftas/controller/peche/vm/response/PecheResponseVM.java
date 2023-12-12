package com.youcode.aftas.controller.peche.vm.response;

import com.youcode.aftas.controller.peche.vm.request.PecheRequestVM;
import com.youcode.aftas.model.entity.Peche;
import jakarta.validation.constraints.NotBlank;

public record PecheResponseVM(
        String competitionCode,
        String membreCode,
        String membreNom,
        String nomPoisson,
        Integer nombrePoisson
) {
    public static PecheResponseVM toVM(Peche peche){
        return new PecheResponseVM(
                peche.getCompetition().getCodeCompetition(),
                peche.getMembre().getNumeroAdhesion(),
                peche.getMembre().getNomMembre(),
                peche.getPoisson().getNomPoisson(),
                peche.getNombrePoisson()
        );
    }
}
