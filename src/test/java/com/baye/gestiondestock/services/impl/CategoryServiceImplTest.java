package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.CategoryDto;
import com.baye.gestiondestock.exception.EntityNotFoundException;
import com.baye.gestiondestock.exception.ErrorCodes;
import com.baye.gestiondestock.exception.InvalidEntityException;
import com.baye.gestiondestock.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService service ;

    @Test
    public void shouldSaveCategoryWithSuccess(){
        CategoryDto expectedCategory = CategoryDto.builder()
                .codeCategory("Cat test")
                .designation("Designation test")
                .idEntreprise(1)
                .build() ;

        CategoryDto savedCategory = service.save(expectedCategory);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals(expectedCategory.getCodeCategory(),savedCategory.getCodeCategory());
        assertEquals(expectedCategory.getDesignation(),savedCategory.getDesignation());
        assertEquals(expectedCategory.getIdEntreprise(),savedCategory.getIdEntreprise());

    }


    @Test
    public void shouldThrowInvalidEntityException(){
        CategoryDto expectedCategory = CategoryDto.builder().build() ;

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class,()->service.save(expectedCategory));

        assertEquals(ErrorCodes.CATEGORY_NOT_VALID,expectedException.getErrorCodes());

        assertEquals(1,expectedException.getErrors().size());

        assertEquals("Veuillez renseigner le code de la catégorie", expectedException.getErrors().get(0));

    }

    @Test
    public void shouldThrowEntityNotFoundException(){
        CategoryDto expectedCategory = CategoryDto.builder().build() ;

        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class,()->service.findById(0));

        assertEquals(ErrorCodes.CATEGORY_NOT_FOUND,expectedException.getErrorCodes());

        assertEquals("Aucun category avec l'ID = 0 a été trouvé dans la BDD",expectedException.getMessage());
    }

    @Test()  // @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundException2(){
        service.findById(0);
    }



}