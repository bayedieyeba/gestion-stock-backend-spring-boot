package com.baye.gestiondestock.controller.api;

import com.baye.gestiondestock.dto.EntrepriseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import static com.baye.gestiondestock.utils.Constants.*;
import java.util.List;

@Tag(name = "Entreprise")
public interface EntrepriseApi {

    @PostMapping(ENTREPRISE_ENDPOINT+"/create")
    EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(ENTREPRISE_ENDPOINT+"/{idEntreprise}")
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(ENTREPRISE_ENDPOINT+"/all")
    List<EntrepriseDto> findAll();

    @DeleteMapping(ENTREPRISE_ENDPOINT+ "/delete/{idEntreprise}")
    void delete(@PathVariable("idEntreprise") Integer id);
}
