package com.youcode.aftas.controller.membre;

import com.youcode.aftas.controller.membre.vm.request.MembreRequestVM;
import com.youcode.aftas.controller.membre.vm.response.MembreResponseVM;
import com.youcode.aftas.handler.response.ResponseMessage;
import com.youcode.aftas.model.entity.Membre;
import com.youcode.aftas.service.membre.MembreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/membre")
public class MembreController {
    private final MembreService membreService;

    public MembreController(MembreService membreService) {
        this.membreService = membreService;
    }

    @GetMapping("{id")
    public ResponseEntity<?> getMembreDetail(@PathVariable Long id){
        MembreResponseVM membreResponseVM = MembreResponseVM.toVM(membreService.getMembreById(id));
        return ResponseMessage.ok(membreResponseVM, "Membre Récuperé avec Succé");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllMembre(){
        List<Membre> membres = membreService.getAllMembres();
        if(membres.isEmpty()){
            return ResponseMessage.notFound("Aucun Membre trouvé");
        }else {
            return ResponseMessage.ok(membres.stream()
                    .map(MembreResponseVM::toVM)
                    .collect(Collectors.toList()), "Membres Réscupérés avec succé");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createMembre(@RequestBody MembreRequestVM membreRequestVM){
        Membre membre = membreRequestVM.toEntite();
        Membre membreCreated = membreService.createMembre(membre);
        return ResponseMessage.created(
                MembreResponseVM.toVM(membreCreated),
                "Membre Crée avec succé"
        );
    }
}

