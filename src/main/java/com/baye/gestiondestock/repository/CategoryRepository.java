package com.baye.gestiondestock.repository;

import com.baye.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Integer, Category> {
}
