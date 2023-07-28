package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.CommandeFournisseurDto;
import com.baye.gestiondestock.dto.LigneVenteDto;
import com.baye.gestiondestock.dto.VenteDto;
import com.baye.gestiondestock.exception.EntityNotFoundException;
import com.baye.gestiondestock.exception.ErrorCodes;
import com.baye.gestiondestock.exception.InvalidEntityException;
import com.baye.gestiondestock.model.Article;
import com.baye.gestiondestock.model.LigneVente;
import com.baye.gestiondestock.model.Ventes;
import com.baye.gestiondestock.repository.ArticleRepository;
import com.baye.gestiondestock.repository.LigneVenteRepository;
import com.baye.gestiondestock.repository.VentesRepository;
import com.baye.gestiondestock.services.VentesService;
import com.baye.gestiondestock.validator.VentesValidator;
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
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository ;

    @Override
    public VenteDto save(VenteDto venteDto) {

        List<String> errors = VentesValidator.validate(venteDto);

        if(!errors.isEmpty()){
            log.error("Ventes n'est pas valide");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID,errors);
        }
        List<String> articleErrors = new ArrayList<>();

        venteDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticleDto().getId());
            if(article.isEmpty()){
                    articleErrors.add("Aucun article avec l'ID "+ ligneVenteDto.getArticleDto().getId() +" n'a été trouvé dans la BDD");
            }
        });
        if(!articleErrors.isEmpty()){
            log.error("One or more articles were not found in the DB");
            throw new InvalidEntityException("Un ou plusieurs articles n'ont pas été trouvé dans la BDD", ErrorCodes.VENTE_NOT_VALID,errors);
        }

        Ventes savedVentes = ventesRepository.save(VenteDto.toEntity(venteDto));

        venteDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVentes(savedVentes);
            ligneVenteRepository.save(ligneVente);
        });
        return VenteDto.fromEntity(savedVentes);
    }

    @Override
    public VenteDto findById(Integer id) {
        if(id == null){
            log.error("Ventes  ID is NULL");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune vente n'a été trouvée dans la BDD", ErrorCodes.VENTE_NOT_FOUND))
                ;
    }

    @Override
    public VenteDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Ventes  ID is NULL");
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VenteDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune vente n'a été trouvée dans la BDD", ErrorCodes.VENTE_NOT_VALID))
                ;
    }

    @Override
    public List<VenteDto> findAll() {

        return ventesRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Ventes ID is NUMM");
            return ;
        }

        ventesRepository.deleteById(id);
    }
}
