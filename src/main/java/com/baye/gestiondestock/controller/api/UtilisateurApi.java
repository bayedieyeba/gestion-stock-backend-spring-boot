package com.baye.gestiondestock.controller.api;

import com.baye.gestiondestock.dto.UtilisateurDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import static com.baye.gestiondestock.utils.Constants.UTILISATEUR_ENDPOINT;
import java.util.List;


@Tag(name = "Utilisateurs")
public interface UtilisateurApi {

    @PostMapping(UTILISATEUR_ENDPOINT+"/create")
    UtilisateurDto save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(UTILISATEUR_ENDPOINT+"/{idUtilisateur}")
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(UTILISATEUR_ENDPOINT+"/all")
    List<UtilisateurDto> findAll();

    @DeleteMapping(UTILISATEUR_ENDPOINT+"/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);
}
