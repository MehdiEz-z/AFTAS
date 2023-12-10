package com.youcode.aftas.service.niveau.implementation;

import com.youcode.aftas.handler.exception.OperationsException;
import com.youcode.aftas.handler.exception.ResourceNotFoundException;
import com.youcode.aftas.model.entity.Membre;
import com.youcode.aftas.model.entity.Niveau;
import com.youcode.aftas.repository.NiveauRepository;
import com.youcode.aftas.service.niveau.NiveauService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NiveauServiceImpl implements NiveauService {
    private final NiveauRepository niveauRepository;

    public NiveauServiceImpl(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    @Override
    public Niveau createNiveau(Niveau niveau) {
        if(niveauRepository.existsByCodeNiveau(niveau.getCodeNiveau())){
            throw new OperationsException("Ce code : " + niveau.getCodeNiveau() + ", existe déjà");
        }
        validateNiveau(niveau);
        return niveauRepository.save(niveau);
    }

    @Override
    public Niveau getNiveauByCode(Integer code) {
        return niveauRepository.getByCodeNiveau(code)
                .orElseThrow(() -> new ResourceNotFoundException("Le Code : " + code + ", n'existe pas"));
    }


    @Override
    public List<Niveau> getAllNiveaux() {
        return niveauRepository.findAll();
    }

    @Override
    public Niveau updateNiveau(Niveau niveau, Integer code) {
        Optional<Niveau> niveauOptionalExiste = niveauRepository.getByCodeNiveau(code);
        if(niveauOptionalExiste.isPresent()){
            Niveau niveauExiste = niveauOptionalExiste.get();
            if(niveauExiste.getCodeNiveau().equals(niveau.getCodeNiveau())
                    && niveauRepository.existsByCodeNiveau(niveau.getCodeNiveau())){
                throw new OperationsException("Le code fourni : " + niveau.getCodeNiveau() + ", appartient déja à un niveau");
            }
            validateNiveau(niveau);
            niveauExiste.setCodeNiveau(niveau.getCodeNiveau());
            niveauExiste.setDescriptionNiveau(niveau.getDescriptionNiveau());
            niveauExiste.setPoints(niveau.getPoints());
            return niveauRepository.save(niveauExiste);
        }else {
            throw new OperationsException("Le niveau avec le code : " + code + ", n'existe pas");
        }
    }

    private void validateNiveau(Niveau niveau) {
        Niveau niveauPrecedent = niveauRepository.findTopByOrderByCodeNiveauDesc();
        if (niveauPrecedent != null && niveau.getCodeNiveau() <= niveauPrecedent.getCodeNiveau()) {
            throw new OperationsException("Le niveau doit être supérieur à celui que vous avez spécifié");
        }
        if (niveauPrecedent != null && niveau.getPoints() <= niveauPrecedent.getPoints()) {
            throw new OperationsException("Les points doivent être supérieurs à : " + niveauPrecedent.getPoints());
        }
    }

    @Override
    public void deleteNiveau(Integer code) {
        Optional<Niveau> niveauExiste = niveauRepository.getByCodeNiveau(code);
        if(niveauExiste.isPresent()){
            niveauRepository.deleteByCodeNiveau(code);
        }else{
            throw new ResourceNotFoundException("Le niveau avec le code : " + code + ", n'existe pas");
        }
    }
}
