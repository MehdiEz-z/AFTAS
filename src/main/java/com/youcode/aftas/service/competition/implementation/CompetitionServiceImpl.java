package com.youcode.aftas.service.competition.implementation;

import com.youcode.aftas.handler.exception.OperationsException;
import com.youcode.aftas.handler.exception.ResourceNotFoundException;
import com.youcode.aftas.model.entity.Classement;
import com.youcode.aftas.model.entity.Competition;
import com.youcode.aftas.model.entity.Membre;
import com.youcode.aftas.repository.ClassementRepository;
import com.youcode.aftas.repository.CompetitionRepository;
import com.youcode.aftas.service.classement.ClassementService;
import com.youcode.aftas.service.competition.CompetitionService;
import com.youcode.aftas.service.membre.MembreService;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final MembreService membreService;
    private final ClassementService classementService;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, MembreService membreService, ClassementRepository classementRepository, ClassementService classementService) {
        this.competitionRepository = competitionRepository;
        this.membreService = membreService;
        this.classementService = classementService;
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

    @Override
    public Classement inscrireMembre(Classement classement) {
        Competition competition = getCompetitionByCode(classement.getCompetition().getCodeCompetition());
        Membre adherent = membreService.getMembreByNombreAdhesion(classement.getMembre().getNumeroAdhesion());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDateTime = competition.getDateCompetition().atTime(competition.getHeureFin());
        if (now.isAfter(endDateTime)) {
            throw new OperationsException("La compétition est déjà terminée. Aucune inscription n'est autorisée.");
        }
        LocalDateTime dateLimiteInscription = competition.getDateCompetition()
                .atTime(competition.getHeureDebut())
                .minusHours(24);
        if (now.isAfter(dateLimiteInscription)) {
            throw new OperationsException("La période d'inscription pour cette compétition est terminée.");
        }
        if (classementService.isMembreInscrit(adherent, competition)) {
            throw new OperationsException("Le membre est déjà inscrit à cette compétition.");
        }
        int nombreParticipantsInscrits = competition.getClassements().size();
        if (nombreParticipantsInscrits >= competition.getNombreParticipants()) {
            throw new OperationsException("Le nombre maximal de participants pour cette compétition a été atteint.");
        }
        classement.setClassementMembre(0);
        classement.setCompetition(competition);
        classement.setMembre(adherent);
        classement.setScore(0);
        return classementService.ajoutClassement(classement);
    }

}
