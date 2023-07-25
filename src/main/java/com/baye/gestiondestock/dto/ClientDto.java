package com.baye.gestiondestock.dto;

import com.baye.gestiondestock.model.Adresse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private Adresse adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    private List<CommandeClientDto> commandeClientDtos;
}
