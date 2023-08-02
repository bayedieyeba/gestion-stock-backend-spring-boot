package com.baye.gestiondestock.controller.api;

import com.baye.gestiondestock.dto.CommandeClientDto;
import com.baye.gestiondestock.model.EtatCommande;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.baye.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name ="CommandeClient")
public interface CommandeClientApi {

    @PatchMapping(value = APP_ROOT+ "/commandesclients/update/etat/{idCommande}/{etatCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un article", description = "Cette méthode permet d'enregistrer ou modifier un article",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ), @ApiResponse(responseCode = "400", description = "L'objet article n'est pas valide")
            }
    )
    @Hidden
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande")EtatCommande etatCommande);

    @PostMapping(value = APP_ROOT+ "/commandesclients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un article", description = "Cette méthode permet d'enregistrer ou modifier un article",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ), @ApiResponse(responseCode = "400", description = "L'objet article n'est pas valide")
            }
    )
    @Hidden
    ResponseEntity<CommandeClientDto> save(@RequestBody  CommandeClientDto commandeClientDto);

    @PatchMapping(value = APP_ROOT+ "/commandesclients/update/quantite/{idCommande}/{idLigneCommande}/{quantite}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un article", description = "Cette méthode permet d'enregistrer ou modifier un article",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ), @ApiResponse(responseCode = "400", description = "L'objet article n'est pas valide")
            }
    )
    @Hidden
    ResponseEntity<CommandeClientDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
                                                             @PathVariable("idLigneCommande") Integer idLigneCommande,
                                                             @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(value = APP_ROOT+ "/commandesclients/update/client/{idCommande}/{idClient}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un article", description = "Cette méthode permet d'enregistrer ou modifier un article",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ), @ApiResponse(responseCode = "400", description = "L'objet article n'est pas valide")
            }
    )
    @Hidden
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande") Integer idCommande,
                                                             @PathVariable("idClient") Integer idClient
                                                             );
    @GetMapping(value = APP_ROOT+"/commandesclients/{idCommandeClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un article par ID", description = "Cette méthode permet de chercher un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<CommandeClientDto> findById(@PathVariable("idCommandeClient") Integer id);

    @GetMapping(value = APP_ROOT+"/commandesclients/{codeCommandeClient}",produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(summary = "Rechercher un article par CODE", description = "Cette méthode permet de chercher un article par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la BDD"),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BDD avec le CODE fourni")
    })
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping(value = APP_ROOT+"/commandesclients/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des articles", description = "Cette méthode permet de chercher et renvoyer la liste des articles qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des articles / Une liste vide" )
    })
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT+"/commandesclients/delete/{idCommandeClient}")
    @Operation(summary = "Supprimer un article", description = "Cette méthode permet de supprimer un article par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été supprimé dans la BDD"),
    })
    ResponseEntity delete(@PathVariable("idCommandeClient") Integer id);
}
