package com.baye.gestiondestock.dto;

import com.baye.gestiondestock.model.Client;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private Client client;

    private Integer idEntreprise;

    private List<LigneCommandeClientDto> ligneCommandeClientDtos;
}
