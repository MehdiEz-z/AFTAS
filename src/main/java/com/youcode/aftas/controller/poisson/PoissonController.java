package com.youcode.aftas.controller.poisson;

import com.youcode.aftas.controller.poisson.vm.request.PoissonRequestVM;
import com.youcode.aftas.controller.poisson.vm.response.PoissonResponseVM;
import com.youcode.aftas.handler.response.ResponseMessage;
import com.youcode.aftas.model.entity.Poisson;
import com.youcode.aftas.service.poisson.PoissonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/poisson")
public class PoissonController {
    private final PoissonService poissonService;

    public PoissonController(PoissonService poissonService) {
        this.poissonService = poissonService;
    }

    @GetMapping("{nomPoisson}")
    public ResponseEntity<?> getPoissonDetail(@PathVariable String nomPoisson){
        PoissonResponseVM poissonResponseVM = PoissonResponseVM.toVM(poissonService.getPoissonByNom(nomPoisson));
        return ResponseMessage.ok(poissonResponseVM,"Poisson Récuperé avec succé");
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPoisson(){
        List<Poisson> poissons = poissonService.getAllPoissons();
        if(poissons.isEmpty()){
            return ResponseMessage.notFound("Aucun Poisson trouvé");
        }else{
            return ResponseMessage.ok(poissons.stream()
                    .map(PoissonResponseVM::toVM)
                    .collect(Collectors.toList()), "Poissons Récupérés avec succé");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createPoisson(@Valid @RequestBody PoissonRequestVM poissonRequestVM){
        Poisson poisson = poissonRequestVM.toEntite();
        Poisson poissonCreated = poissonService.createPoisson(poisson);
        return ResponseMessage.created(
                PoissonResponseVM.toVM(poissonCreated),
                "Poisson crée avec succé"
        );
    }
}
