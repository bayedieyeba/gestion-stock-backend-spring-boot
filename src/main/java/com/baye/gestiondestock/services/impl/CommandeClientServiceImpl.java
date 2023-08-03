package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.ArticleDto;
import com.baye.gestiondestock.dto.ClientDto;
import com.baye.gestiondestock.dto.CommandeClientDto;
import com.baye.gestiondestock.dto.LigneCommandeClientDto;
import com.baye.gestiondestock.exception.EntityNotFoundException;
import com.baye.gestiondestock.exception.ErrorCodes;
import com.baye.gestiondestock.exception.InvalidEntityException;
import com.baye.gestiondestock.exception.InvalidOperationException;
import com.baye.gestiondestock.model.*;
import com.baye.gestiondestock.repository.ArticleRepository;
import com.baye.gestiondestock.repository.ClientRepository;
import com.baye.gestiondestock.repository.CommandeClientRepository;
import com.baye.gestiondestock.repository.LigneCommandeClientRepository;
import com.baye.gestiondestock.services.CommandeClientService;
import com.baye.gestiondestock.validator.ArticleValidator;
import com.baye.gestiondestock.validator.CommandeClientValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    private LigneCommandeClientRepository ligneCommandeClientRepository;

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {

        List<String> errors = CommandeClientValidator.validate(commandeClientDto);

        if(!errors.isEmpty()){
            log.error("Commande client n'est pas valid");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }

        if(commandeClientDto.getId() != null && commandeClientDto.isCommandeLivree()){
                throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",
                        ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if(client.isEmpty()){
            log.warn("Client with ID {} was not found in the DB",commandeClientDto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID "+commandeClientDto.getClient().getId()+" n'a été trouvé dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>() ;

        if(commandeClientDto.getLigneCommandeClientDtos() !=null){
            commandeClientDto.getLigneCommandeClientDtos().forEach(ligCmdClt ->{
                if(ligCmdClt.getArticleDto() !=null){
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticleDto().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'ID " +ligCmdClt.getArticleDto().getId() + " n'existe pas");
                    }
                    else{
                        articleErrors.add("Impossible d'enregistrer une commande un article NULL");
                    }
                }

            });
        }
        if(!articleErrors.isEmpty()){
            log.warn("");
            throw  new InvalidEntityException("Article n'existe pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }
       CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));

        if(commandeClientDto.getLigneCommandeClientDtos() !=null){
            commandeClientDto.getLigneCommandeClientDtos().forEach(ligCmdClt->{
                LigneCommandeClient  ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if(id == null){
            log.error("Commande client ID is NULL");
            return null;
        }

        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucune commande client n'a été trouvée avec l'ID "+id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ))
                ;
    }


    @Override
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {

        checkIdCommande(idCommande);

        if(StringUtils.hasLength(String.valueOf(etatCommande))){
            log.error("L'etat de la commande client est NULL");
            throw new InvalidOperationException("Impossible de modifier l'etat de la  commande avec un etat null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        CommandeClientDto commandeClient = checkEtatCommande(idCommande);
        commandeClient.setEtatCommande(etatCommande);

        return CommandeClientDto.fromEntity(
                commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient)
                )
        );
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        if(quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0){
            log.error("L'ID de la ligne de commande est NULL");
            throw new InvalidOperationException("Impossible de modifier la quantité de la  commande avec une quantite null ou 0",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        checkEtatCommande(idCommande);

        Optional<LigneCommandeClient> ligneCommandeClientOptional  = findLigneCommandeClient(idLigneCommande);
         LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
        ligneCommandeClient.setQuantite(quantite);

        ligneCommandeClientRepository.save(ligneCommandeClient);
        return null;
    }


    @Override
    public CommandeClientDto findByCode(String code) {
        if(StringUtils.hasLength(code)){
            log.error("Commande client CODE is NULL");
            return null;
        }

        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucune commande client n'a été trouvée avec le CODE "+code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ))
                ;
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        commandeClientRepository.deleteById(id);

    }

    @Override
    public List<LigneCommandeClientDto> findAllLigneCommandesClientByCommandeClientId(Integer idCommande) {

        return ligneCommandeClientRepository.findAllByCommandeClientId(idCommande).stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {

        checkIdCommande(idCommande);
        if(idClient == null){
            log.error("L'ID de la ligne de commande est NULL");
            throw new InvalidOperationException("Impossible de modifier l'etat de la  commande avec un ID client null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        CommandeClientDto commandeClient =  checkEtatCommande(idCommande);

        Optional<Client> clientOptional = clientRepository.findById(idClient);


        if(clientOptional.isEmpty()){
            throw new EntityNotFoundException(
                    "Aucun client n'a été trouvée avec l'ID "+idClient, ErrorCodes.CLIENT_NOT_FOUND
            );
        }
        commandeClient.setClient(ClientDto.fromEntity(clientOptional.get()));
        return CommandeClientDto.fromEntity(
                commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient))
        );
    }

    @Override
    public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        CommandeClientDto commandeClient = checkEtatCommande(idCommande);

        Optional<LigneCommandeClient> ligneCommandeClient = findLigneCommandeClient(idLigneCommande);

        ligneCommandeClientRepository.deleteById(idLigneCommande);
        return null;
    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idArticle, "nouvel");

        CommandeClientDto commandeClient = checkEtatCommande(idCommande);

        Optional<LigneCommandeClient> ligneCommandeClient = findLigneCommandeClient(idLigneCommande);

        Optional<Article> articleOptional = articleRepository.findById(idArticle);

        if(articleOptional.isEmpty()){
            throw new EntityNotFoundException(
                    "Aucun article n'a été trouvée avec l'ID "+idArticle, ErrorCodes.ARTICLE_NOT_FOUND
            );
        }
       List<String> errors =  ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));

        if(!errors.isEmpty()){
            throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        LigneCommandeClient ligneCommandeClientToSaved = ligneCommandeClient.get();
        ligneCommandeClientToSaved.setArticle(articleOptional.get());
        ligneCommandeClientRepository.save(ligneCommandeClientToSaved);

        return commandeClient;
    }


    private CommandeClientDto checkEtatCommande(Integer idCommande){

        CommandeClientDto commandeClient = findById(idCommande);
        if(commandeClient.isCommandeLivree()){

            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        return commandeClient ;
    }
    private void checkIdCommande(Integer idCommande){
        if(idCommande == null){
            log.error("Commande client ID is NULL");
            throw new InvalidOperationException("Impossible de modifier l'etat de la  commande avec un ID null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private void checkIdLigneCommande(Integer idLigneCommande){
        if(idLigneCommande == null){
            log.error("L'ID de la ligne de commande est NULL");
            throw new InvalidOperationException("Impossible de modifier la quantité de la  commande avec une ligne de commande  null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private void checkIdArticle(Integer idArticle, String msg){
        if(idArticle == null){
            log.error("L'ID de "+ msg +" est NULL");
            throw new InvalidOperationException("Impossible de modifier l'etat de la  commande avec un " + msg +"ID article null ",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private Optional<LigneCommandeClient> findLigneCommandeClient(Integer idLigneCommande) {
        Optional<LigneCommandeClient> ligneCommandeClientOptional =  ligneCommandeClientRepository.findById(idLigneCommande);

        if(ligneCommandeClientOptional.isEmpty()){
            throw new EntityNotFoundException(
                    "Aucune ligne commande client n'a été trouvée avec l'ID "+idLigneCommande, ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND
            );
        }
        return ligneCommandeClientOptional;
    }
}
