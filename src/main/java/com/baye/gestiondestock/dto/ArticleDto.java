package com.baye.gestiondestock.dto;
import com.baye.gestiondestock.model.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class ArticleDto {

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private Integer idEntreprise;

    private CategoryDto categoryDto;

    private List<LigneVente> ligneVentes ;

    private List<LigneCommandeClient> ligneCommandeClients;

    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs ;

    private List<MvtStk> mvtStks;

    public  static ArticleDto fromEntity(Article article){

        if(article ==null){
            return null ;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .ligneCommandeClients(article.getLigneCommandeClients())
                .ligneCommandeFournisseurs(article.getLigneCommandeFournisseurs())
                .idEntreprise(article.getIdEntreprise())
                .mvtStks(article.getMvtStks())
                .ligneVentes(article.getLigneVentes())
                .photo(article.getPhoto())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .tauxTva(article.getTauxTva())
                .categoryDto(CategoryDto.fromEntity(article.getCategory()))
                .build();
    }

    public static Article toEntity(ArticleDto articleDto) {
        if(articleDto == null) {
            return null ;
        }
        Article article = new Article() ;
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPhoto(articleDto.getPhoto());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        article.setMvtStks(articleDto.getMvtStks());
        article.setLigneCommandeClients(articleDto.getLigneCommandeClients());
        article.setLigneCommandeFournisseurs(articleDto.getLigneCommandeFournisseurs());
        article.setLigneVentes(articleDto.getLigneVentes());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setTauxTva(articleDto.getTauxTva());
        article.setCategory(CategoryDto.toEntity(articleDto.getCategoryDto()));
        return article ;
    }
}
