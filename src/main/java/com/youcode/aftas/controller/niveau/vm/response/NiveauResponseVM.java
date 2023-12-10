package com.youcode.aftas.controller.niveau.vm.response;

import com.youcode.aftas.model.entity.Niveau;

public record NiveauResponseVM(
        Integer code,
        String description,
        int points
) {
    public static NiveauResponseVM toVM(Niveau niveau){
        return new NiveauResponseVM(
                niveau.getCodeNiveau(),
                niveau.getDescriptionNiveau(),
                niveau.getPoints()
        );
    }
}
