package com.baye.gestiondestock.repository;

import com.baye.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Integer, Entreprise> {
}
