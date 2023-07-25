package com.baye.gestiondestock.dto;
import com.baye.gestiondestock.model.Adresse;
import com.baye.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
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

    public  AdresseDto fromEntity(Adresse adresse){

        if(adresse ==null){
            return null ;
        }
        return AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .codePostale(adresse.getCodePostale())
                .ville(adresse.getVille())
                .pays(adresse.getPays())
                .build();
    }

    public Adresse toEntity(AdresseDto adresseDto) {
        if(adresseDto == null) {
            return null ;
        }
        Adresse adresse = new Adresse() ;
        adresse.setAdresse1(adresse.getAdresse1());
        adresse.setAdresse2(adresseDto.getAdresse2());
        adresse.setCodePostale(adresseDto.getCodePostale());
        adresse.setVille(adresseDto.getVille());
        adresse.setPays(adresseDto.getPays());

        return adresse ;
    }
}
