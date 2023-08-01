package com.baye.gestiondestock.validator;

import com.baye.gestiondestock.dto.CategoryDto;
import com.baye.gestiondestock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto entrepriseDto){

        List<String> errors = new ArrayList<>();

        if(entrepriseDto == null || !StringUtils.hasLength(entrepriseDto.getNom())){
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }
        return errors;
    }
}
