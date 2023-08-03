package com.baye.gestiondestock.repository;

import com.baye.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient,Integer> {

    List<LigneCommandeClient> findAllByArticleId(Integer idArticle);
    List<LigneCommandeClient>  findAllByCommandeClientId(Integer id);
}
