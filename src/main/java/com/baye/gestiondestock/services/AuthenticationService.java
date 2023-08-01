package com.baye.gestiondestock.services;


import com.baye.gestiondestock.dto.auth.AuthenticationRequest;
import com.baye.gestiondestock.dto.auth.AuthenticationResponse;
import com.baye.gestiondestock.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository repository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var utilisateur = repository.findUtilisateurByEmail(request.getEmail())
                .orElseThrow();

        return  AuthenticationResponse.builder()
                .accessToken("token")
                .build();
    }

}
