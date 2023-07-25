package com.baye.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class VenteDto {

    private Integer id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    private Integer idEntreprise;
}
