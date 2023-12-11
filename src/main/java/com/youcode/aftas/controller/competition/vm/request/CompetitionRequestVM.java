package com.youcode.aftas.controller.competition.vm.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.aftas.model.entity.Competition;
import com.youcode.aftas.model.enums.StatutCompetition;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionRequestVM(
    @JsonIgnore
    String code,
    @NotNull(message = "La Date de Competition est obligatoire")
    LocalDate dateCompetition,
    @NotNull(message = "L'heure de debut de la competition est obligatoire")
    LocalTime debut,
    @NotNull(message = "L'heure de fin de la competition est obligatoire")
    LocalTime fin,
    @Min(value =1, message = "Le Nombre des Participants est Obligatoire")
    @Positive(message = "Le Nombre des Participants doit être supérieur a 0")
    @Digits(integer = 10, fraction = 0, message = "Le Nombre des Participants doit être un nombre entier positif")
    int nombre,
    @NotBlank(message = "Le Lieu est Obligatoire")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-]*$", message = "Le Lieu ne doit pas contenir des caractères spéciaux autres que \"-\"")
    String lieu,
    @NotNull(message = "Le prix  de la participation est Obligatoire")
    @Digits(integer = 10, fraction = 2, message = "Le prix doit être une valeur numérique avec jusqu'à 2 décimales")
    @Positive(message = "Le prix doit être supérieur a 0")
    Double prix
) {
    public Competition toEntite(){
        return Competition.builder()
                .codeCompetition(code)
                .dateCompetition(dateCompetition)
                .heureDebut(debut)
                .heureFin(fin)
                .nombreParticipants(nombre)
                .location(lieu)
                .prixParticipation(prix)
                .statutCompetition(StatutCompetition.OUVERTE)
                .build();
    }
}
