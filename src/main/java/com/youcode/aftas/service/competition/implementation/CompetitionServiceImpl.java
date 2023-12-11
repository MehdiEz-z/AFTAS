package com.youcode.aftas.service.competition.implementation;

import com.youcode.aftas.handler.exception.OperationsException;
import com.youcode.aftas.handler.exception.ResourceNotFoundException;
import com.youcode.aftas.model.entity.Competition;
import com.youcode.aftas.repository.CompetitionRepository;
import com.youcode.aftas.service.competition.CompetitionService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition createCompetition(Competition competition) {
        LocalDate dateDebutCompetition = competition.getDateCompetition();
        LocalDate dateCreation = LocalDate.now();
        if (dateCreation.plusDays(3).isAfter(dateDebutCompetition)) {
            throw new OperationsException("La date de création de la compétition doit être inférieure à la date de début de compétition de 3 jours");
        }
        if (competitionRepository.existsByDateCompetition(dateDebutCompetition)) {
            throw new OperationsException("Une compétition existe déjà pour la date : " + dateDebutCompetition);
        }
        if (competition.getHeureFin().isBefore(competition.getHeureDebut())) {
            throw new OperationsException("L'heure de fin doit être supérieure à l'heure de début");
        }
        String codeCompetition = generateCodeCompetition(competition);
        competition.setCodeCompetition(codeCompetition);
        return competitionRepository.save(competition);
    }
    private String generateCodeCompetition(Competition competition) {
        String troisPremieresLettresNom = competition.getLocation().substring(0, Math.min(competition.getLocation().length(), 3)).toUpperCase();
        String dateCompetition = competition.getDateCompetition().format(DateTimeFormatter.ofPattern("yy-MM-dd"));

        return troisPremieresLettresNom + "-" + dateCompetition;
    }
    @Override
    public Competition getCompetitionByCode(String codeCompetition) {
        return competitionRepository.findByCodeCompetition(codeCompetition)
                .orElseThrow(() -> new ResourceNotFoundException("La Competition avec le code : " + codeCompetition + ", n'existe pas"));
    }

    @Override
    public List<Competition> getAllCompetition() {
        return competitionRepository.findAll();
    }

    @Override
    public Competition updateCompetition(Competition competition, String codeCompetition) {
        return null;
    }

    @Override
    public void deleteCompetition(String codeCompetition) {

    }
}
