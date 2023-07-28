package com.baye.gestiondestock.controller;

import com.baye.gestiondestock.controller.api.CategoryApi;
import com.baye.gestiondestock.dto.CategoryDto;
import com.baye.gestiondestock.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController implements CategoryApi {

    private CategoryService categoryService;
    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryService.findById(id);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public CategoryDto findByCode(String code) {
        return categoryService.findByCode(code);
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }
}
