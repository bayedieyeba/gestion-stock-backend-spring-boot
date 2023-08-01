package com.baye.gestiondestock.dto;

import com.baye.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private Instant dateDeNaissance;

    private String motDePasse;

    private AdresseDto adresseDto;

    private String photo;

    private EntrepriseDto entrepriseDto;

    private List<RoleDto> roleDtos;


    public  static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if(utilisateur == null){
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .motDePasse(utilisateur.getMotDePasse())
                .adresseDto(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .photo(utilisateur.getPhoto())
                .entrepriseDto(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){
        if(utilisateurDto == null){
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresseDto()));
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntrepriseDto()));
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        return utilisateur ;
    }
}
