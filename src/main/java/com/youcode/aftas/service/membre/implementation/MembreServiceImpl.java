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
        String numeroAdhesion = generateNumeroAdhesion(membre);
        if(membreRepository.existsByNumeroAdhesion(numeroAdhesion)){
            throw new OperationsException("Ce membre existe deja");
        }
        membre.setNumeroAdhesion(numeroAdhesion);
        return membreRepository.save(membre);
    }

    private String generateNumeroAdhesion(Membre membre) {
        String deuxPremieresLettresNom = membre.getNomMembre().substring(0, Math.min(membre.getNomMembre().length(), 2)).toUpperCase();
        String deuxPremieresLettresNationalite = membre.getNationaliteMembre().substring(0, Math.min(membre.getNationaliteMembre().length(), 2)).toUpperCase();
        String anneeNaissance = String.valueOf(membre.getDateNaissanceMembre().getYear());
        String numeroAdhesionBase = deuxPremieresLettresNom + "-" + deuxPremieresLettresNationalite + "-" + anneeNaissance;
        int suffixe = 1;
        String numeroAdhesion = numeroAdhesionBase + "-" + suffixe;
        while (membreRepository.existsByNumeroAdhesion(numeroAdhesion)) {
            suffixe++;
            numeroAdhesion = numeroAdhesionBase + "-" + suffixe;
        }
        return numeroAdhesion;
    }


    @Override
    public Membre getMembreByNombreAdhesion(String nombreAdhesion) {
        return membreRepository.findByNumeroAdhesion(nombreAdhesion)
                .orElseThrow(() -> new ResourceNotFoundException("Le membre avec le code : " + nombreAdhesion + ", n'existe pas"));
    }

    @Override
    public Membre getMembreById(Long id) {
        return membreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ce Membre n'existe pas"));
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
            membreExiste.setDateNaissanceMembre(membre.getDateNaissanceMembre());
            membreExiste.setNationaliteMembre(membre.getNationaliteMembre());
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
