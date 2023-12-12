package com.youcode.aftas.controller.competition.vm.response;

import com.youcode.aftas.model.entity.Competition;

import java.util.List;

public record CompetitionWithClassementResponseVM(
    String codeCompetition,
    List<ListClassementResponseVM> Classements
) {
    public static CompetitionWithClassementResponseVM toVM(Competition competition){
        return new CompetitionWithClassementResponseVM(
                competition.getCodeCompetition(),
                competition.getClassements().stream().map(ListClassementResponseVM::toVM).toList()
        );
    }
}
