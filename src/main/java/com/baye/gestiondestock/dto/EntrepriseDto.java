package com.baye.gestiondestock.dto;


import com.baye.gestiondestock.model.Entreprise;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class EntrepriseDto {

    private Integer id;

    private String nom;

    private String description;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTel;

    private AdresseDto adresseDto ;

    private String siteWeb;

    private List<UtilisateurDto> utilisateurDtos;



    public static   EntrepriseDto fromEntity(Entreprise entreprise){
        if(entreprise == null){
            return null;
        }

        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .adresseDto(AdresseDto.fromEntity(entreprise.getAdresse()))
                .description(entreprise.getDescription())
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .email(entreprise.getEmail())
                .numTel(entreprise.getNumTel())
                .siteWeb(entreprise.getSiteWeb())
                .build();
    }


    public static Entreprise toEntity(EntrepriseDto entrepriseDto){
        if(entrepriseDto == null){
            return   null;
        }

        Entreprise entreprise = new Entreprise();

        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setNom(entrepriseDto.getNom());
        entreprise.setEmail(entreprise.getEmail());
        entreprise.setNumTel(entrepriseDto.getNumTel());
        entreprise.setAdresse(AdresseDto.toEntity(entrepriseDto.getAdresseDto()));
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
        entreprise.setSiteWeb(entrepriseDto.getSiteWeb());

        return entreprise ;
    }
}
