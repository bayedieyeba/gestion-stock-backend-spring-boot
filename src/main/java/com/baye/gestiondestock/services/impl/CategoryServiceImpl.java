package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.CategoryDto;
import com.baye.gestiondestock.exception.EntityNotFoundException;
import com.baye.gestiondestock.exception.ErrorCodes;
import com.baye.gestiondestock.exception.InvalidEntityException;
import com.baye.gestiondestock.repository.CategoryRepository;
import com.baye.gestiondestock.services.CategoryService;
import com.baye.gestiondestock.validator.CategoryValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Override
    public CategoryDto save(CategoryDto categoryDto) {

        List<String> errors = CategoryValidator.validate(categoryDto) ;

        if(!errors.isEmpty()){
           log.error("Category is not valid {}" ,categoryDto);
           throw  new InvalidEntityException("La catégorie n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID,errors);
        }

        return  CategoryDto.fromEntity(
                categoryRepository.save(CategoryDto.toEntity(categoryDto))
        ) ;
    }

    @Override
    public CategoryDto findById(Integer id) {

        if(id == null){
            log.error("Category ID is null");
            return null ;
        }
        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun category avec l'ID = "+id+ "n'a été trouvé dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ))
                ;
    }

    @Override
    public CategoryDto findByCode(String code) {
        if(StringUtils.hasLength(code)){
            log.error(("Category CODE is null"));
            return null ;
        }
        return categoryRepository.findCategoryByCodeCategory(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun category avec l'CODE = "+code+ "n'a été trouvé dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ))
                ;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());

    }



    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Category ID is null");
            return ;
        }

        categoryRepository.deleteById(id);
    }
}
