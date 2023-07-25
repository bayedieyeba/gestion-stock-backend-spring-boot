package com.baye.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresseDto;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    private List<CommandeFournisseurDto> commandeFournisseurDtos;
}
