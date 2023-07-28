package com.baye.gestiondestock.controller;

import com.baye.gestiondestock.controller.api.FournisseurApi;
import com.baye.gestiondestock.dto.FournisseurDto;
import com.baye.gestiondestock.services.FournisseurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FournisseurController implements FournisseurApi {
    private FournisseurService fournisseurService;

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        return fournisseurService.save(fournisseurDto);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        fournisseurService.delete(id);
    }
}
