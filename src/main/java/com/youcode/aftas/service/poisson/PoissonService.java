package com.youcode.aftas.service.poisson;

import com.youcode.aftas.model.entity.Poisson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PoissonService {
    Poisson createPoisson(Poisson poisson);
    Poisson getPoissonByNom(String nomPoisson);
    List<Poisson> getAllPoissons();
    Poisson updatePoisson(Poisson poisson, String nomPoisson);
    void deletePoisson(String nomPoisson);
}
