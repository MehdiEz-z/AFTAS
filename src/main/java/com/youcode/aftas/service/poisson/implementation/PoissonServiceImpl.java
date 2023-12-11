package com.youcode.aftas.service.poisson.implementation;

import com.youcode.aftas.handler.exception.OperationsException;
import com.youcode.aftas.handler.exception.ResourceNotFoundException;
import com.youcode.aftas.model.entity.Niveau;
import com.youcode.aftas.model.entity.Poisson;
import com.youcode.aftas.repository.PoissonRepository;
import com.youcode.aftas.service.niveau.NiveauService;
import com.youcode.aftas.service.poisson.PoissonService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PoissonServiceImpl implements PoissonService {
    private final PoissonRepository poissonRepository;
    private final NiveauService niveauService;

    public PoissonServiceImpl(PoissonRepository poissonRepository, NiveauService niveauService) {
        this.poissonRepository = poissonRepository;
        this.niveauService = niveauService;
    }

    @Override
    public Poisson createPoisson(Poisson poisson) {
        validatePoisson(poisson);
        poisson.setNiveau(poisson.getNiveau());
        return poissonRepository.save(poisson);
    }

    @Override
    public Poisson getPoissonByNom(String nomPoisson) {
        return poissonRepository.findByNomPoisson(nomPoisson)
                .orElseThrow(() -> new ResourceNotFoundException("Le Poisson : " + nomPoisson + ", n'existe pas"));
    }

    @Override
    public List<Poisson> getAllPoissons() {
        return poissonRepository.findAll();
    }

    @Override
    public Poisson updatePoisson(Poisson poisson, String nomPoisson) {
        Optional<Poisson> existingPoissonOptional =poissonRepository.findByNomPoisson(nomPoisson);
        if(existingPoissonOptional.isPresent()){
            validatePoisson(poisson);
            poisson.setNiveau(poisson.getNiveau());
            poisson.setNomPoisson(poisson.getNomPoisson());
            poisson.setPoidsMoyen(poisson.getPoidsMoyen());
        }else{
            throw new ResourceNotFoundException("Le Poisson : " + nomPoisson + ", n'existe pas");
        }
        return null;
    }

    private void validatePoisson(Poisson poisson) {
        Niveau niveauExiste = niveauService.getNiveauByCode(poisson.getNiveau().getCodeNiveau());
        if(niveauExiste == null){
            throw new ResourceNotFoundException("Ce Niveau n'existe pas");
        }
        if(poissonRepository.existsByNomPoisson(poisson.getNomPoisson())){
            throw new OperationsException("Ce Poisson existe déjà");
        }
    }

    @Override
    public void deletePoisson(String nomPoisson) {
        Optional<Poisson> poissonExiste = poissonRepository.findByNomPoisson(nomPoisson);
        if(poissonExiste.isPresent()){
            poissonRepository.deleteByNomPoisson(nomPoisson);
        }else{
            throw new ResourceNotFoundException("Le Poisson : " + nomPoisson + ", n'existe pas");
        }
    }
}
