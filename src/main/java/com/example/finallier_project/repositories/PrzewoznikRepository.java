package com.example.finallier_project.repositories;

import com.example.finallier_project.models.Przewoznik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrzewoznikRepository extends JpaRepository<Przewoznik,Long> {
    Przewoznik findByNazwa(String nazwa);
}
