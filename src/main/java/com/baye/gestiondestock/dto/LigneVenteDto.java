package com.baye.gestiondestock.dto;


import com.baye.gestiondestock.model.LigneVente;
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
    private ArticleDto articleDto;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){

        if(ligneVente == null){
            return null;
        }

        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .venteDto(VenteDto.fromEntity(ligneVente.getVentes()))
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .idEntreprise(ligneVente.getIdEntreprise())
                .articleDto(ArticleDto.fromEntity(ligneVente.getArticle()))
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto){

        if(ligneVenteDto == null){
            return null ;
        }

        LigneVente ligneVente = new LigneVente() ;
        ligneVente.setVentes(VenteDto.toEntity(ligneVenteDto.getVenteDto()));
        ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
        ligneVente.setArticle(ArticleDto.toEntity(ligneVenteDto.getArticleDto()));
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());

        return ligneVente;
    }
}
