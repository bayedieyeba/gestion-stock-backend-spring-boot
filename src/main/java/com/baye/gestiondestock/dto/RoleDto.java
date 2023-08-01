package com.baye.gestiondestock.dto;


import com.baye.gestiondestock.model.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private Integer id;
    private String roleName;

    private UtilisateurDto utilisateurDto;

    private Integer idEntreprise;

    public static RoleDto fromEntity(Roles roles){
        if(roles == null){
            return null ;
        }
        return RoleDto.builder()
                .utilisateurDto(UtilisateurDto.fromEntity(roles.getUtilisateur()))
                .roleName(roles.getRoleName())
                .idEntreprise(roles.getIdEntreprise())
                .build();
    }

    public static Roles toEntity(RoleDto roleDto){
        if(roleDto == null){
            return null;
        }

        Roles roles = new Roles() ;

        roles.setRoleName(roleDto.getRoleName());
        roles.setUtilisateur(UtilisateurDto.toEntity(roleDto.getUtilisateurDto()));
        roles.setIdEntreprise(roleDto.getIdEntreprise());

        return roles ;
    }

}
