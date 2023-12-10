package com.youcode.aftas.service.niveau;

import com.youcode.aftas.model.entity.Membre;
import com.youcode.aftas.model.entity.Niveau;
import jakarta.servlet.annotation.ServletSecurity;

import java.util.List;

@ServletSecurity
public interface NiveauService {
    Niveau createNiveau(Niveau niveau);
    Niveau getNiveauByCode(Integer code);
    List<Niveau> getAllNiveaux();
    Niveau updateNiveau(Niveau niveau, Integer code);
    void deleteNiveau(Integer code);
}
