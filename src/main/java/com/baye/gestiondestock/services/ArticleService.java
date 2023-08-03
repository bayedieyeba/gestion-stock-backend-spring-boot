package com.baye.gestiondestock.services;

import com.baye.gestiondestock.dto.ArticleDto;
import com.baye.gestiondestock.dto.LigneCommandeClientDto;
import com.baye.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.baye.gestiondestock.dto.LigneVenteDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto articleDto);

    ArticleDto findById(Integer id);

    ArticleDto findByCodeArticle(String codeArticle);

    List<ArticleDto> findAll();

    List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);

    List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle);

    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);

    List<ArticleDto> findAllArticleByCategory(Integer idCategory);

    void delete(Integer id);
}
