package com.youcode.aftas.controller.poisson.vm.response;

import com.youcode.aftas.model.entity.Poisson;

public record PoissonResponseVM(
        String nom,
        Double poidsMoyen,
        Integer code,
        String description,
        int points
) {
    public static PoissonResponseVM toVM(Poisson poisson){
        return new PoissonResponseVM(
            poisson.getNomPoisson(),
            poisson.getPoidsMoyen(),
            poisson.getNiveau().getCodeNiveau(),
            poisson.getNiveau().getDescriptionNiveau(),
            poisson.getNiveau().getPoints()
        );
    }
}
