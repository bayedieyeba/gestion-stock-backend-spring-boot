package com.baye.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "entreprises")
public class Entreprise extends AbstractEntity {

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "codefiscal")
    private String codeFiscal;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email")
    private String email;

    @Column(name = "numtel")
    private String numTel;

    @Embedded
    private Adresse adresse;

    @Column(name = "siteweb")
    private String siteWeb;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;
}
