package com.baye.gestiondestock.services.impl;

import com.baye.gestiondestock.dto.ClientDto;
import com.baye.gestiondestock.exception.EntityNotFoundException;
import com.baye.gestiondestock.exception.ErrorCodes;
import com.baye.gestiondestock.exception.InvalidEntityException;
import com.baye.gestiondestock.repository.ClientRepository;
import com.baye.gestiondestock.services.ClientService;
import com.baye.gestiondestock.validator.ClientValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);

        if(!errors.isEmpty()){
            log.error("Client is not valid {}" ,clientDto);
            throw  new InvalidEntityException("le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID,errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(ClientDto.toEntity(clientDto))
        );
    }

    @Override
    public ClientDto findById(Integer id) {
        if(id == null){
            log.error("Client ID is null");
            return null ;
        }
        return clientRepository.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun category avec l'ID = "+id+ "n'a été trouvé dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND
                ))
                ;
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if(id == null){
            log.error("Client ID is null");
            return ;
        }

        clientRepository.deleteById(id);
    }
}
