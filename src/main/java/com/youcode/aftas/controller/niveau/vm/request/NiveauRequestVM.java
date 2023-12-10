package com.youcode.aftas.controller.niveau.vm.request;

import com.youcode.aftas.model.entity.Niveau;
import jakarta.validation.constraints.*;

public record NiveauRequestVM(
        @NotNull(message = "Le Code est Obligatoire")
        Integer code,
        @NotBlank(message = "La Description est Obligatoire")
        @Pattern(regexp = "^[.,\\p{L}0-9\\s]+$", message = "La Description ne doit pas contenir des caractére spéciaux")
        @Size(max = 200, message = "La Description ne doit pas dépassé 200 caractère")
        String description,
        @Min(value =1, message = "Les Points du niveau sont Obligatoire")
        @Positive(message = "Les Points du niveau doivent être supérieur a 0")
        @Digits(integer = 10, fraction = 0, message = "Les points du niveau doivent être un nombre entier positif")
        int points
) {
    public  Niveau toEntite(){
        return Niveau.builder()
                .codeNiveau(code)
                .descriptionNiveau(description)
                .points(points)
                .build();
    }
}
