package com.baye.gestiondestock.controller.api;

import com.baye.gestiondestock.dto.VenteDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.baye.gestiondestock.utils.Constants.VENTES_ENDPOINT;

@Tag(name = "Ventes")
public interface VentesApis {

    @PostMapping(VENTES_ENDPOINT+"/create")
    VenteDto save(VenteDto venteDto);

    @GetMapping(VENTES_ENDPOINT+"/{idVentes}")
    VenteDto findById(@PathVariable("idVentes") Integer id);

    @GetMapping(VENTES_ENDPOINT+"/{codeVentes}")
    VenteDto findByCode(@PathVariable("codeVentes") String code);

    @GetMapping(VENTES_ENDPOINT+"/all")
    List<VenteDto> findAll();

    @GetMapping(VENTES_ENDPOINT+"/delete/{idVentes}")
    void delete(@PathVariable("idVentes") Integer id);
}
