package com.baye.gestiondestock.dto;

import com.baye.gestiondestock.model.Adresse;
import com.baye.gestiondestock.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    @JsonIgnore
    private List<CommandeClientDto> commandeClientDtos;


    public static ClientDto fromEntity(Client client){
        if(client == null){
            return  null;
        }
        return ClientDto.builder()
                .adresse(AdresseDto.fromEntity(client.getAdresse()))
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .idEntreprise(client.getIdEntreprise())
                .numTel(client.getNumTel())
                .mail(client.getMail())
                .photo(client.getPhoto())
                .build();
    }

    public static Client toEntity(ClientDto clientDto){

        Client client = new Client();

        client.setAdresse(AdresseDto.toEntity(clientDto.getAdresse()));
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setPhoto(clientDto.getPhoto());
        client.setIdEntreprise(clientDto.getIdEntreprise());
        client.setNumTel(clientDto.getNumTel());
        client.setMail(clientDto.getMail());
        //client.setCommandeClients(CommandeClientDto.toEntity(clientDto.getCommandeClientDtos()));

        return client;
    }
}
