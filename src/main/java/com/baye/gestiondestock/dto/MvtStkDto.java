package com.baye.gestiondestock.dto;
import com.baye.gestiondestock.model.MvtStk;
import com.baye.gestiondestock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

    private Integer id;
    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto articleDto;

    private TypeMvtStk typeMvt;

    private Integer idEntreprise;

    public static MvtStkDto fromEntity(MvtStk mvtStk){
        if(mvtStk == null) {
            return null ;
        }

        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .idEntreprise(mvtStk.getIdEntreprise())
                .articleDto(ArticleDto.fromEntity(mvtStk.getArticle()))
                .typeMvt(mvtStk.getTypeMvt())
                .build();
    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto){

        if(mvtStkDto == null) {
            return null ;
        }

        MvtStk mvtStk = new MvtStk() ;

        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setTypeMvt(mvtStkDto.getTypeMvt());
        mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticleDto()));
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setIdEntreprise(mvtStkDto.getIdEntreprise());
        return  mvtStk ;
    }
}
