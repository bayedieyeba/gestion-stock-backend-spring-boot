package com.baye.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lignecommandefournisseur" )
public class LigneCommandeFournisseur  extends  AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idcommandefournisseur")
    private CommandeFournisseur commandeFournisseur;

    @JoinColumn(name = "quantite")
    private BigDecimal quantite;

    @JoinColumn(name = "prixunitaire")
    private BigDecimal prixUnitaire;

}
