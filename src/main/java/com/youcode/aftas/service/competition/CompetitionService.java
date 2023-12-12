package com.youcode.aftas.service.competition;

import com.youcode.aftas.model.entity.Classement;
import com.youcode.aftas.model.entity.Competition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompetitionService {
    Competition createCompetition(Competition competition);
    Competition getCompetitionByCode(String codeCompetition);
    List<Competition> getAllCompetition();
    Competition updateCompetition(Competition competition, String codeCompetition);
    void deleteCompetition(String codeCompetition);
    Classement inscrireMembre(Classement classement);
    Competition updateClassementMembre(String competitionCode);
}
