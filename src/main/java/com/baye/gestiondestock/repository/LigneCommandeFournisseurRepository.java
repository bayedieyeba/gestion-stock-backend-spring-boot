package com.baye.gestiondestock.repository;

import com.baye.gestiondestock.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur,Integer> {

    List<LigneCommandeFournisseur> findAllByArticleId(Integer idArticle);
}
