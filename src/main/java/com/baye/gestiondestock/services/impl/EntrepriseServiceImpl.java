package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.AdresseDto;
import com.baye.gestiondestock.dto.EntrepriseDto;
import com.baye.gestiondestock.dto.RoleDto;
import com.baye.gestiondestock.dto.UtilisateurDto;
import com.baye.gestiondestock.exception.ErrorCodes;
import com.baye.gestiondestock.exception.InvalidEntityException;
import com.baye.gestiondestock.repository.EntrepriseRepository;
import com.baye.gestiondestock.repository.RolesRepository;
import com.baye.gestiondestock.services.EntrepriseService;
import com.baye.gestiondestock.services.UtilisateurService;
import com.baye.gestiondestock.validator.EntrepriseValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private UtilisateurService utilisateurService;
    private EntrepriseRepository entrepriseRepository;
    private RolesRepository rolesRepository ;
    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {

        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if(!errors.isEmpty()){
            log.error("Entreprise is not valid {}", entrepriseDto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID,errors);
        }

        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto))
        );
        UtilisateurDto utilisateurDto = fromEntreprise(savedEntreprise);

        UtilisateurDto savedUser = utilisateurService.save(utilisateurDto);

        RoleDto roleDto = RoleDto.builder()
                .roleName("ADMIN")
                .utilisateurDto(savedUser)
                .build();

        rolesRepository.save(RoleDto.toEntity(roleDto));

        return savedEntreprise;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto dto){
        return UtilisateurDto.builder()
                .adresseDto(dto.getAdresseDto())
                .nom(dto.getNom())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .motDePasse(generateRandomPassword())
                .entrepriseDto(dto)
                .dateDeNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build() ;
    }

    private String generateRandomPassword(){return "passer";}
    @Override
    public EntrepriseDto findById(Integer id) {
        return null;
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
