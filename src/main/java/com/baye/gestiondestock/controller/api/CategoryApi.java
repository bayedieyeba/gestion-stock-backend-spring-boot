package com.baye.gestiondestock.controller.api;


import com.baye.gestiondestock.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.baye.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name ="Category")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT+ "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une categorie", description = "Cette méthode permet d'enregistrer ou modifier une categorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200",description = "L'objet categorie créé / modifié"),
            @ApiResponse(responseCode = "400", description = "L'objet categorie n'est pas valide")
    })
    CategoryDto save(@RequestBody  CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT+"/categories/{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une categorie par ID", description = "Cette méthode permet de chercher une categorie par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La categorie a été trouvée dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucune categorie n'existe dans la BDD avec l'ID fourni")
    })


    CategoryDto findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT+"/categories/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des categories", description = "Cette méthode permet de chercher et renvoyer la liste des categories qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des categories / Une liste vide" )
    })
    List<CategoryDto> findAll();

    @GetMapping(value = APP_ROOT+"/categories/{codeCategorie}",produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(summary = "Rechercher un categorie par CODE", description = "Cette méthode permet de chercher une categorie par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La categorie a été trouvée dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucune categorie n'existe dans la BDD avec le CODE fourni")
    })


    CategoryDto findByCode(@PathVariable("codeCategorie") String code) ;

    @DeleteMapping(value = APP_ROOT+"/categories/delete/{idCategory}")
    @Operation(summary = "Supprimer une categorie", description = "Cette méthode permet de supprimer une categorie par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'categorie a été supprimé dans la BDD"),
    })
    void delete(@PathVariable("idCategory") Integer id);
}
