package com.youcode.aftas.service.classement.implementation;

import com.youcode.aftas.model.entity.Classement;
import com.youcode.aftas.model.entity.Competition;
import com.youcode.aftas.model.entity.Membre;
import com.youcode.aftas.repository.ClassementRepository;
import com.youcode.aftas.service.classement.ClassementService;
import org.springframework.stereotype.Component;

@Component
public class ClassementServiceImpl implements ClassementService {
    private final ClassementRepository classementRepository;

    public ClassementServiceImpl(ClassementRepository classementRepository) {
        this.classementRepository = classementRepository;
    }

    @Override
    public Classement ajoutClassement(Classement classement) {
        return classementRepository.save(classement);
    }

    @Override
    public boolean isMembreInscrit(Membre membre, Competition competition) {
        return classementRepository.existsByMembreAndCompetition(membre, competition);
    }

}
