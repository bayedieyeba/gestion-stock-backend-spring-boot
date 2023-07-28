package com.baye.gestiondestock.dto;

import com.baye.gestiondestock.model.CommandeClient;
import com.baye.gestiondestock.model.LigneCommandeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {

    private Integer id;

    private ArticleDto articleDto;

    @JsonIgnore
    private CommandeClientDto commandeClientDto;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;


    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient){
        if(ligneCommandeClient == null){
            return  null;
        }

        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .articleDto(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto){
        if(ligneCommandeClientDto == null){
            return  null;
        }
        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient() ;

        ligneCommandeClient.setCommandeClient(CommandeClientDto.toEntity(ligneCommandeClientDto.getCommandeClientDto()));
        ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticleDto()));
        ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
        ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
        ligneCommandeClient.setIdEntreprise(ligneCommandeClientDto.getIdEntreprise());

        return ligneCommandeClient;
    }
}
