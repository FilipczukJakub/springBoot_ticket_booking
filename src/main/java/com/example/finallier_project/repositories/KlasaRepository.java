package com.example.finallier_project.repositories;

import com.example.finallier_project.models.Klasa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface KlasaRepository extends JpaRepository<Klasa,Long> {
    Klasa findByNazwa(String nazwa);

}