package com.baye.gestiondestock.controller;

import com.baye.gestiondestock.controller.api.CommandeClientApi;
import com.baye.gestiondestock.dto.CommandeClientDto;
import com.baye.gestiondestock.services.CommandeClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService ;
    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto commandeClientDto) {
        return ResponseEntity.ok(commandeClientService.save(commandeClientDto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
