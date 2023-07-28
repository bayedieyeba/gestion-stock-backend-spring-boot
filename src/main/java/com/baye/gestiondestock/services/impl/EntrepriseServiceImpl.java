package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.EntrepriseDto;
import com.baye.gestiondestock.repository.EntrepriseRepository;
import com.baye.gestiondestock.services.EntrepriseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        return null;
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return null;
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
