package com.youcode.aftas.service.classement;

import com.youcode.aftas.model.entity.Classement;
import com.youcode.aftas.model.entity.Competition;
import com.youcode.aftas.model.entity.Membre;
import org.springframework.stereotype.Service;

@Service
public interface ClassementService {
    Classement ajoutClassement(Classement classement);
    boolean isMembreInscrit(Membre membre, Competition competition);
}
