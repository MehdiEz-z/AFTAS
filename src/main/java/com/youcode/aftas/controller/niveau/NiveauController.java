package com.youcode.aftas.controller.niveau;

import com.youcode.aftas.controller.niveau.vm.request.NiveauRequestVM;
import com.youcode.aftas.controller.niveau.vm.response.NiveauResponseVM;
import com.youcode.aftas.handler.response.ResponseMessage;
import com.youcode.aftas.model.entity.Niveau;
import com.youcode.aftas.service.niveau.NiveauService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/niveau")
public class NiveauController {
    private final NiveauService niveauService;

    public NiveauController(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    @GetMapping("{code}")
    public ResponseEntity<?> getNiveauDetail(@PathVariable Integer code){
        NiveauResponseVM niveauResponseVM = NiveauResponseVM.toVM(niveauService.getNiveauByCode(code));
        return ResponseMessage.ok(niveauResponseVM,"Niveau Récuperé avec succé");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllNiveau(){
        List<Niveau> niveaux = niveauService.getAllNiveaux();
        if(niveaux.isEmpty()){
            return ResponseMessage.notFound("Aucun Niveau trouvé");
        }else {
            return ResponseMessage.ok(niveaux.stream()
                    .map(NiveauResponseVM::toVM)
                    .collect(Collectors.toList()), "Niveaux Récupérés avec succé");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createNiveau(@Valid @RequestBody NiveauRequestVM niveauRequestVM){
        Niveau niveau = niveauRequestVM.toEntite();
        Niveau niveauCreated = niveauService.createNiveau(niveau);
        return ResponseMessage.created(
                NiveauResponseVM.toVM(niveauCreated),
                "Niveau Crée avec succé"
        );
    }
}
