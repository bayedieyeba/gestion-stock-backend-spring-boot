package com.baye.gestiondestock.dto;

import com.baye.gestiondestock.model.Article;
import com.baye.gestiondestock.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {

    private Integer id;

    private String codeCategory;

    private Integer idEntreprise;

    private String designation;

    @JsonIgnore
    private List<Article> articles;

    public static CategoryDto fromEntity(Category category){
        if(category == null){
            return  null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .idEntreprise(category.getIdEntreprise())
                .codeCategory(category.getCodeCategory())
                .designation(category.getDesignation())
                .build();

    }

    public static Category toEntity(CategoryDto categoryDto){
        if(categoryDto == null){
            return null;
        }

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setIdEntreprise(category.getIdEntreprise());
        category.setCodeCategory(categoryDto.getCodeCategory());
        category.setDesignation(categoryDto.getDesignation());

        return category;
    }
}
