package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.UtilisateurDto;
import com.baye.gestiondestock.repository.UtilisateurRepository;
import com.baye.gestiondestock.services.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository ;
    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        return null;
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return null;
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
