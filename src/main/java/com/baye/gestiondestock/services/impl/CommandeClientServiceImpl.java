package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.CommandeClientDto;
import com.baye.gestiondestock.dto.LigneCommandeClientDto;
import com.baye.gestiondestock.exception.EntityNotFoundException;
import com.baye.gestiondestock.exception.ErrorCodes;
import com.baye.gestiondestock.exception.InvalidEntityException;
import com.baye.gestiondestock.model.Article;
import com.baye.gestiondestock.model.Client;
import com.baye.gestiondestock.model.CommandeClient;
import com.baye.gestiondestock.model.LigneCommandeClient;
import com.baye.gestiondestock.repository.ArticleRepository;
import com.baye.gestiondestock.repository.ClientRepository;
import com.baye.gestiondestock.repository.CommandeClientRepository;
import com.baye.gestiondestock.repository.LigneCommandeClientRepository;
import com.baye.gestiondestock.services.CommandeClientService;
import com.baye.gestiondestock.validator.CommandeClientValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        if(id == null){
            log.error("Commande client ID is NUMM");
            return ;
        }

        commandeClientRepository.deleteById(id);
    }
}
