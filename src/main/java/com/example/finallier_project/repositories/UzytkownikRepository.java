package com.example.finallier_project.repositories;

import com.example.finallier_project.models.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UzytkownikRepository extends JpaRepository<Uzytkownik,Long> {
    public Uzytkownik findByUsername(String username);
    public Uzytkownik findById(long id);
}
