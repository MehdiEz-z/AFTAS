package com.youcode.aftas.controller.competition.vm.response;

import com.youcode.aftas.model.entity.Competition;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionResponseVM(
      String code,
      LocalDate dateCompetition,
      LocalTime debut,
      LocalTime fin,
      int nombreParticipants,
      String lieu,
      Double prix,
      String status

) {
    public static CompetitionResponseVM toVM(Competition competition){
        return new CompetitionResponseVM(
                competition.getCodeCompetition(),
                competition.getDateCompetition(),
                competition.getHeureDebut(),
                competition.getHeureFin(),
                competition.getNombreParticipants(),
                competition.getLocation(),
                competition.getPrixParticipation(),
                competition.getStatutCompetition().name()
        );
    }
}
