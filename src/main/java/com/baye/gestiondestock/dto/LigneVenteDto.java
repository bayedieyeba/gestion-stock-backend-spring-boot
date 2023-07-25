package com.baye.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;

    private VenteDto venteDto;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;
}
