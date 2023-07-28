package com.baye.gestiondestock.controller;

import com.baye.gestiondestock.controller.api.VentesApis;
import com.baye.gestiondestock.dto.VenteDto;
import com.baye.gestiondestock.services.VentesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class VentesController implements VentesApis {

    private VentesService ventesService;

    @Override
    public VenteDto save(VenteDto venteDto) {
        return ventesService.save(venteDto);
    }

    @Override
    public VenteDto findById(Integer id) {
        return ventesService.findById(id);
    }

    @Override
    public VenteDto findByCode(String code) {
        return ventesService.findByCode(code);
    }

    @Override
    public List<VenteDto> findAll() {
        return ventesService.findAll();
    }

    @Override
    public void delete(Integer id) {
        ventesService.delete(id);
    }
}
