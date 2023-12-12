package com.youcode.aftas.service.peche;

import com.youcode.aftas.controller.peche.vm.request.PecheRequestVM;
import com.youcode.aftas.model.entity.Peche;
import org.springframework.stereotype.Service;

@Service
public interface PecheService {
    Peche ajoutPeche(PecheRequestVM pecheRequestVM);
}
