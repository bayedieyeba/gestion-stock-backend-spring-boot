package com.baye.gestiondestock.dto;

import com.baye.gestiondestock.model.Fournisseur;
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


    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if(fournisseur ==null){
            return null;
        }

        return FournisseurDto.builder()
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresseDto(AdresseDto.fromEntity(fournisseur.getAdresse()))
                .photo(fournisseur.getPhoto())
                .mail(fournisseur.getMail())
                .numTel(fournisseur.getNumTel())
                .build();
    }

    public static  Fournisseur toEntity(FournisseurDto fournisseurDto){
        if(fournisseurDto ==null){
            return null;
        }

        Fournisseur fournisseur = new Fournisseur() ;
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getNom());
        fournisseur.setAdresse(AdresseDto.toEntity(fournisseurDto.getAdresseDto()));
        fournisseur.setMail(fournisseurDto.getMail());
        fournisseur.setNumTel(fournisseurDto.getNumTel());
        fournisseur.setPhoto(fournisseurDto.getPhoto());

        return fournisseur ;
    }

}
