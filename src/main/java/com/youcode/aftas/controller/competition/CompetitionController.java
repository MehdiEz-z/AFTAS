package com.youcode.aftas.controller.competition;

import com.youcode.aftas.controller.competition.vm.request.CompetitionRequestVM;
import com.youcode.aftas.controller.competition.vm.request.InscriptionRequestVM;
import com.youcode.aftas.controller.competition.vm.response.CompetitionWithClassementResponseVM;
import com.youcode.aftas.controller.competition.vm.response.CompetitionResponseVM;
import com.youcode.aftas.controller.competition.vm.response.InscriptionResponseVM;
import com.youcode.aftas.handler.response.ResponseMessage;
import com.youcode.aftas.model.entity.Classement;
import com.youcode.aftas.model.entity.Competition;
import com.youcode.aftas.service.competition.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/api/competition")
public class CompetitionController {
    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCompetition(){
        List<Competition> competitions = competitionService.getAllCompetition();
        if(competitions.isEmpty()){
            return ResponseMessage.notFound("Aucune Competition trouvée");
        }else{
            return ResponseMessage.ok(competitions.stream()
                    .map(CompetitionResponseVM::toVM).collect(Collectors.toList()),
                    "Competitions récupérées avec succé");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createCompetition(@Valid @RequestBody CompetitionRequestVM competitionRequestVM){
        Competition competition = competitionRequestVM.toEntite();
        Competition competitionCreated = competitionService.createCompetition(competition);
        return ResponseMessage.created(
                CompetitionResponseVM.toVM(competitionCreated),
                "Competition crée avec succé"
        );
    }

    @PostMapping("/inscription")
    public ResponseEntity<?> inscriptionMembre(@Valid @RequestBody InscriptionRequestVM inscriptionRequestVM){
        Classement classement = inscriptionRequestVM.toEntite();
        Classement classementCreated = competitionService.inscrireMembre(classement);
        return ResponseMessage.created(
                InscriptionResponseVM.toVM(classementCreated),
                        "Membre inscrit avec succé dans la competition"
        );
    }

    @PutMapping("{codeCompetition}/classement")
    public ResponseEntity<?> updateClassement(@PathVariable String codeCompetition){
        Competition competition = competitionService.updateClassementMembre(codeCompetition);
        return ResponseMessage.ok(CompetitionWithClassementResponseVM.toVM(competition),
                "Classement modifié avec Succé");
    }
}
