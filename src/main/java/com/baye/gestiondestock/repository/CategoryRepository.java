package com.baye.gestiondestock.repository;

import com.baye.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

   Optional<Category>  findCategoryByCodeCategory(String code);

}
