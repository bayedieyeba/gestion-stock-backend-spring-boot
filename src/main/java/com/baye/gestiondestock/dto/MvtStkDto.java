package com.baye.gestiondestock.dto;
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
}