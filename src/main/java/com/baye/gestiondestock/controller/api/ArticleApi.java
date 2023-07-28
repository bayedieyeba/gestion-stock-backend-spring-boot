package com.baye.gestiondestock.controller.api;

import com.baye.gestiondestock.dto.ArticleDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.baye.gestiondestock.utils.Constants.APP_ROOT;
import java.util.List;

@Tag(name ="Articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT+ "/article/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un article", description = "Cette méthode permet d'enregistrer ou modifier un article",
            responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200"
            ), @ApiResponse(responseCode = "400", description = "L'objet article n'est pas valide")
            }
    )
    @Hidden
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping(value = APP_ROOT+"/article/{idArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un article par ID", description = "Cette méthode permet de chercher un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT+"/article/{codeArticle}",produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(summary = "Rechercher un article par CODE", description = "Cette méthode permet de chercher un article par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BDD avec le CODE fourni")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT+"/article/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des articles", description = "Cette méthode permet de chercher et renvoyer la liste des articles qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des articles / Une liste vide" )
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/article/delete/{idArticle}")
    @Operation(summary = "Supprimer un article", description = "Cette méthode permet de supprimer un article par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été supprimé dans la BDD"),
    })
    void delete(@PathVariable("idArticle") Integer id);
}
