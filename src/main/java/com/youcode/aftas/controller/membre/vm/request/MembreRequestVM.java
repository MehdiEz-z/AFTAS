package com.youcode.aftas.controller.membre.vm.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.aftas.model.entity.Membre;
import com.youcode.aftas.model.enums.IdentityDocumentType;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Member;
import java.time.LocalDate;

public record MembreRequestVM(
    @JsonIgnore
    String numero,
    @NotBlank(message = "Le Nom est Obligatoire")
    @Size(min = 4, max = 15, message = "Le nom doit avoir entre 4 et 15 caractères")
    String nom,
    @NotBlank(message = "Le Prenom est Obligatoire")
    @Size(min = 4, max = 15, message = "Le Prenom doit avoir entre 4 et 15 caractères")
    String prenom,
    @NotNull(message = "La Date est obligatoire")
    @Past(message = "La Date de Naissance doit être dans le passé")
    LocalDate dateNaissance,
    @NotBlank(message = "La Nationalité est Obligatoire")
    String nationalite,
    @NotBlank(message = "Le Type d'identité est Obligatoire")
    @Pattern(regexp = "^(CIN|CARTE_RESIDENCE|PASSPORT)$", message = "Le type d'identité  doit être soit CIN, CARTE_RESIDENCE ou PASSPORT")
    String identite
) {
    public Membre toEntite(){
        return Membre.builder()
                .numeroAdhesion(numero)
                .nomMembre(nom)
                .prenomMembre(prenom)
                .dateNaissanceMembre(dateNaissance)
                .nationaliteMembre(nationalite)
                .identiteType(IdentityDocumentType.valueOf(identite))
                .build();
    }
}
