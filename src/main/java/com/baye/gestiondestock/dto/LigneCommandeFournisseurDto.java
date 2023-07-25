package com.baye.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurDto {

    private Integer id;

    private ArticleDto articleDto;

    private CommandeFournisseurDto commandeFournisseurDto;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;
}
