package com.youcode.aftas.controller.peche;

import com.youcode.aftas.controller.peche.vm.request.PecheRequestVM;
import com.youcode.aftas.controller.peche.vm.response.PecheResponseVM;
import com.youcode.aftas.handler.response.ResponseMessage;
import com.youcode.aftas.model.entity.Peche;
import com.youcode.aftas.service.peche.PecheService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/peche")
public class PecheController {
    private final PecheService pecheService;

    public PecheController(PecheService pecheService) {
        this.pecheService = pecheService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addPeche(@Valid @RequestBody PecheRequestVM pecheRequestVM){
        Peche peche = pecheService.ajoutPeche(pecheRequestVM);
        return ResponseMessage.created(
                PecheResponseVM.toVM(peche),
                "Peche crée avec succé"
        );
    }
}
