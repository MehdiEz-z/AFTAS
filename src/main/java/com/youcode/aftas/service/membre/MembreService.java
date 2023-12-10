package com.youcode.aftas.service.membre;

import com.youcode.aftas.model.entity.Membre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MembreService {
    Membre createMembre(Membre membre);
    Membre getMembreByNombreAdhesion(String nombreAdhesion);
    List<Membre> getAllMembres();
    List<Membre> searchMembres(String searchTerm);
    Membre updateMembre(Membre membre, String nombreAdhesion);
    void deleteMembre( String nombreAdhesion);
}
