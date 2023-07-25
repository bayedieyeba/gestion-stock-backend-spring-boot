package com.baye.gestiondestock.repository;

import com.baye.gestiondestock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentesRepository extends JpaRepository<Integer, Ventes> {
}
