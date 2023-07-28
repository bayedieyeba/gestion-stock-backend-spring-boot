package com.baye.gestiondestock.services;


import com.baye.gestiondestock.dto.VenteDto;

import java.util.List;

public interface VentesService {

    VenteDto save(VenteDto venteDto);

    VenteDto findById(Integer id);

    VenteDto findByCode(String code);

    List<VenteDto> findAll();

    void delete(Integer id);
}
