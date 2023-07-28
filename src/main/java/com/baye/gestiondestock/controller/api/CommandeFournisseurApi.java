package com.baye.gestiondestock.controller.api;

import com.baye.gestiondestock.dto.CommandeFournisseurDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.baye.gestiondestock.utils.Constants.*;

@Tag(name ="Fournisseur")
public interface CommandeFournisseurApi {


    @PostMapping(value = CREATE_COMMANDE_FOURNISSEUR_ENDPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un article", description = "Cette méthode permet d'enregistrer ou modifier un article",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ), @ApiResponse(responseCode = "400", description = "L'objet article n'est pas valide")
            }
    )
    @Hidden
    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(value = FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un article par ID", description = "Cette méthode permet de chercher un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    CommandeFournisseurDto findById(@PathVariable("idCommandeFourniseur") Integer id);

    @GetMapping(value = FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT,produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(summary = "Rechercher un article par CODE", description = "Cette méthode permet de chercher un article par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BDD avec le CODE fourni")
    })
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFourniseur") String code);

    @GetMapping(value = FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des articles", description = "Cette méthode permet de chercher et renvoyer la liste des articles qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des articles / Une liste vide" )
    })
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = DELETE_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT)
    @Operation(summary = "Supprimer un article", description = "Cette méthode permet de supprimer un article par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été supprimé dans la BDD"),
    })
    void delete(@PathVariable("idCommandeFourniseur")Integer id);
}
