package com.baye.gestiondestock.controller.api;

import com.baye.gestiondestock.dto.FournisseurDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.baye.gestiondestock.utils.Constants.FOURNISSEUR_ENDPOINT;

import java.util.List;

@Tag(name = "Fournisseurs")
public interface FournisseurApi {

    @PostMapping(FOURNISSEUR_ENDPOINT+"/create")
    FournisseurDto save(FournisseurDto fournisseurDto);

    @GetMapping(FOURNISSEUR_ENDPOINT+"/{idFournisseur}")
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(FOURNISSEUR_ENDPOINT+"all")
    List<FournisseurDto> findAll();

    @DeleteMapping(FOURNISSEUR_ENDPOINT+"/delete/{idFournisseur}")
    void delete(@PathVariable("idFournisseur") Integer id);
}
