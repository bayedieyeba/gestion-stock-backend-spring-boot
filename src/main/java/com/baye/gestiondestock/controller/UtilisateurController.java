package com.baye.gestiondestock.controller;

import com.baye.gestiondestock.controller.api.UtilisateurApi;
import com.baye.gestiondestock.dto.UtilisateurDto;
import com.baye.gestiondestock.services.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        return utilisateurService.save(utilisateurDto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}
