package com.baye.gestiondestock.repository;

import com.baye.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Integer, Article> {

}
