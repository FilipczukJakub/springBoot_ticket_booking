package com.example.finallier_project.repositories;

import com.example.finallier_project.models.Kraj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KrajRepository extends JpaRepository<Kraj,Long> {
    Kraj findByNazwa(String nazwa);
}
