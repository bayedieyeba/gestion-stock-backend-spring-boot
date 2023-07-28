package com.baye.gestiondestock.controller;

import com.baye.gestiondestock.controller.api.EntrepriseApi;
import com.baye.gestiondestock.dto.EntrepriseDto;
import com.baye.gestiondestock.services.EntrepriseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor

public class EntrepriseController implements EntrepriseApi {

    private EntrepriseService entrepriseService;

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        return entrepriseService.save(entrepriseDto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void delete(Integer id) {
        entrepriseService.delete(id);
    }
}
