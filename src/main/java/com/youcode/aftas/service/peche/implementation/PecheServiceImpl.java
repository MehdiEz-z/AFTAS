package com.youcode.aftas.service.peche.implementation;

import com.youcode.aftas.controller.peche.vm.request.PecheRequestVM;
import com.youcode.aftas.handler.exception.OperationsException;
import com.youcode.aftas.model.entity.*;
import com.youcode.aftas.model.enums.StatutCompetition;
import com.youcode.aftas.repository.ClassementRepository;
import com.youcode.aftas.repository.PecheRepository;
import com.youcode.aftas.service.competition.CompetitionService;
import com.youcode.aftas.service.membre.MembreService;
import com.youcode.aftas.service.peche.PecheService;
import com.youcode.aftas.service.poisson.PoissonService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PecheServiceImpl implements PecheService {
    private final PecheRepository pecheRepository;
    private final MembreService membreService;
    private final PoissonService poissonService;
    private final CompetitionService competitionService;
    private final ClassementRepository classementRepository;

    public PecheServiceImpl(PecheRepository pecheRepository, MembreService membreService, PoissonService poissonService, CompetitionService competitionService, ClassementRepository classementRepository) {
        this.pecheRepository = pecheRepository;
        this.membreService = membreService;
        this.poissonService = poissonService;
        this.competitionService = competitionService;
        this.classementRepository = classementRepository;
    }


    @Override
    public Peche ajoutPeche(PecheRequestVM pecheRequestVM) {
        Competition competition = competitionService.getCompetitionByCode(pecheRequestVM.competitionCode());
        if (competition.getStatutCompetition() != StatutCompetition.EN_COURS) {
            throw new OperationsException("La compétition n'a pas débuté'. Impossible d'ajouter une pêche.");
        }
        Membre membre = membreService.getMembreByNombreAdhesion(pecheRequestVM.membreCode());
        if (!classementRepository.existsByMembreAndCompetition(membre, competition)) {
            throw new OperationsException("Le membre ne participe pas à cette compétition.");
        }
        Poisson poisson = poissonService.getPoissonByNom(pecheRequestVM.nomPoisson());
        double poidsPoisson = Double.parseDouble(String.valueOf(pecheRequestVM.poidsPoisson()));
        if (poidsPoisson < poisson.getPoidsMoyen()) {
            throw new OperationsException("Le poids du poisson pêché est inférieur au poids moyen du poisson. La pêche est rejetée.");
        }
        Peche pecheExiste = pecheRepository.findByPoissonAndMembreAndCompetition(poisson, membre, competition);
        if (pecheExiste != null) {
            pecheExiste.setNombrePoisson(pecheExiste.getNombrePoisson() + 1);
            return pecheRepository.save(pecheExiste);
        } else {
            Peche nouvellePeche = pecheRequestVM.toEntite();
            nouvellePeche.setNombrePoisson(1);
            nouvellePeche.setMembre(membre);
            nouvellePeche.setCompetition(competition);
            nouvellePeche.setPoisson(poisson);
            return pecheRepository.save(nouvellePeche);
        }
    }
}
