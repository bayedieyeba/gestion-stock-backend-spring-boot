package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.FournisseurDto;
import com.baye.gestiondestock.repository.FournisseurRepository;
import com.baye.gestiondestock.services.FournisseurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        return null;
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return null;
    }

    @Override
    public List<FournisseurDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
