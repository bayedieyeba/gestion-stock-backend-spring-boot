package com.baye.gestiondestock.dto;


import com.baye.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VenteDto {

    private Integer id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    private Integer idEntreprise;

    private List<LigneVenteDto> ligneVenteDtos;

    public static VenteDto fromEntity(Ventes ventes){
        if(ventes ==null ){
            return null;
        }

        return VenteDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commentaire(ventes.getCommentaire())
                .idEntreprise(ventes.getIdEntreprise())
                .build();
    }

    public static Ventes toEntity(VenteDto venteDto){

        if(venteDto ==null ){
            return null;
        }
        Ventes ventes = new Ventes() ;

        ventes.setDateVente(venteDto.getDateVente());
        ventes.setCode(venteDto.getCode());
        ventes.setCommentaire(venteDto.getCommentaire());
        ventes.setIdEntreprise(venteDto.getIdEntreprise());

        return ventes ;
    }
}
