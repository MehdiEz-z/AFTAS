package com.youcode.aftas.service.membre.implementation;

import com.youcode.aftas.handler.exception.OperationsException;
import com.youcode.aftas.handler.exception.ResourceNotFoundException;
import com.youcode.aftas.model.entity.Membre;
import com.youcode.aftas.repository.MembreRepository;
import com.youcode.aftas.service.membre.MembreService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MembreServiceImpl implements MembreService {
    private final MembreRepository membreRepository;

    public MembreServiceImpl(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }


    @Override
    public Membre createMembre(Membre membre) {
        if(membreRepository.existsByNumeroAdhesion(membre.getNumeroAdhesion())){
            throw new OperationsException("Ce code : " + membre.getNumeroAdhesion() + ", appartient déja à un membre");
        }
        return membreRepository.save(membre);
    }

    @Override
    public Membre getMembreByNombreAdhesion(String nombreAdhesion) {
        return membreRepository.findByNumeroAdhesion(nombreAdhesion)
                .orElseThrow(() -> new ResourceNotFoundException("Le membre avec le code : " + nombreAdhesion + ", n'existe pas"));
    }

    @Override
    public List<Membre> getAllMembres() {
        return membreRepository.findAll();
    }

    @Override
    public List<Membre> searchMembres(String searchTerm) {
        return membreRepository.findByNumeroAdhesionStartingWithIgnoreCaseOrNomMembreStartingWithIgnoreCaseOrPrenomMembreStartingWithIgnoreCase(searchTerm, searchTerm, searchTerm);
    }

    @Override
    public Membre updateMembre(Membre membre, String nombreAdhesion) {
        Optional<Membre> membreOptionalExiste = membreRepository.findByNumeroAdhesion(nombreAdhesion);
        if(membreOptionalExiste.isPresent()){
            Membre membreExiste = membreOptionalExiste.get();
            if(membreExiste.getNumeroAdhesion().equals(membre.getNumeroAdhesion())
                && membreRepository.existsByNumeroAdhesion(membre.getNumeroAdhesion())){
                throw new OperationsException("Le code fourni : " + membre.getNumeroAdhesion() + ", appartient déja à un membre");
            }
            membreExiste.setNomMembre(membre.getNomMembre());
            membreExiste.setPrenomMembre(membre.getPrenomMembre());
            membreExiste.setDateAdhesion(membre.getDateAdhesion());
            membreExiste.setNationalite(membre.getNationalite());
            membreExiste.setIdentiteType(membre.getIdentiteType());
            membreExiste.setNumeroAdhesion(membre.getNumeroAdhesion());
            return membreRepository.save(membreExiste);
        }else{
            throw new OperationsException("Le membre avec le code : " + nombreAdhesion + ", n'existe pas");
        }
    }

    @Override
    public void deleteMembre(String nombreAdhesion) {
        Optional<Membre> membreExiste = membreRepository.findByNumeroAdhesion(nombreAdhesion);
        if(membreExiste.isPresent()){
            membreRepository.deleteByNumeroAdhesion(nombreAdhesion);
        }else{
            throw new ResourceNotFoundException("Le membre avec le code : " + nombreAdhesion + ", n'existe pas");
        }
    }
}
