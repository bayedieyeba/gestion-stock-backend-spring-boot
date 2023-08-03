package com.baye.gestiondestock.controller;


import com.baye.gestiondestock.controller.api.ArticleApi;
import com.baye.gestiondestock.dto.ArticleDto;
import com.baye.gestiondestock.dto.LigneCommandeClientDto;
import com.baye.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.baye.gestiondestock.dto.LigneVenteDto;
import com.baye.gestiondestock.services.ArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Articles")
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        return articleService.save(articleDto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
        return articleService.findHistoriqueVentes(idArticle);
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle) {
        return articleService.findHistoriqueCommandeClient(idArticle);
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return articleService.findHistoriqueCommandeFournisseur(idArticle);
    }

    @Override
    public List<ArticleDto> findAllArticleByCategory(Integer idCategory) {
        return articleService.findAllArticleByCategory(idCategory);
    }

    @Override
    public void delete(Integer id) {
            articleService.delete(id);
    }
}
