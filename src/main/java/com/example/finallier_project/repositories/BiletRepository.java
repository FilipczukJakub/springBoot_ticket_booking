package com.example.finallier_project.repositories;

import com.example.finallier_project.models.Bilet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiletRepository extends JpaRepository<Bilet,Long> {
    public List<Bilet> findAllByUzytkownikUsername(String username);
}
