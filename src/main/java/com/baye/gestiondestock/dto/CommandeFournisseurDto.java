package com.baye.gestiondestock.dto;

import com.baye.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;


import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private Fournisseur fournisseur;

    private Integer idEntreprise;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurDtos;
}
