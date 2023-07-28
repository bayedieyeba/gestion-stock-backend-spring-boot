package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.CommandeFournisseurDto;
import com.baye.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.baye.gestiondestock.exception.EntityNotFoundException;
import com.baye.gestiondestock.exception.ErrorCodes;
import com.baye.gestiondestock.exception.InvalidEntityException;
import com.baye.gestiondestock.model.*;
import com.baye.gestiondestock.repository.*;
import com.baye.gestiondestock.services.CommandeFournisseurService;
import com.baye.gestiondestock.validator.CommandeFournisseurValidator;
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
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository clientRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {

        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);

        if(!errors.isEmpty()){
            log.error("Commande fournisseur n'est pas valid");
            throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID,errors);
        }
        Optional<Fournisseur> fournisseur = clientRepository.findById(commandeFournisseurDto.getFournisseur().getId());
        if(fournisseur.isEmpty()){
            log.warn("Fournisseur with ID {} was not found in the DB",commandeFournisseurDto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID "+commandeFournisseurDto.getFournisseur().getId()+" n'a été trouvé dans la BDD", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>() ;

        if(commandeFournisseurDto.getLigneCommandeFournisseurDtos() !=null){
            commandeFournisseurDto.getLigneCommandeFournisseurDtos().forEach(ligCmdFrs ->{
                if(ligCmdFrs.getArticleDto() !=null){
                    Optional<Article> article = articleRepository.findById(ligCmdFrs.getArticleDto().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'ID " +ligCmdFrs.getArticleDto().getId() + " n'existe pas");
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
        CommandeFournisseur savedCmdFrs = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

        if(commandeFournisseurDto.getLigneCommandeFournisseurDtos() !=null){
            commandeFournisseurDto.getLigneCommandeFournisseurDtos().forEach(ligCmdFrs->{
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFrs);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFrs);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }

        return CommandeFournisseurDto.fromEntity(savedCmdFrs);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if(id == null){
            log.error("Commande fournisseur ID is NULL");
            return null;
        }

        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucune commande fournisseur n'a été trouvée avec l'ID "+id, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ))
                ;
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if(StringUtils.hasLength(code)){
            log.error("Commande fournisseur CODE is NULL");
            return null;
        }

        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucune commande fournisseur n'a été trouvée avec le CODE "+code, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ))
                ;
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Commande fournisseur ID is NUMM");
            return ;
        }

        commandeFournisseurRepository.deleteById(id);
    }
}
