package com.example.finallier_project.services;

import com.example.finallier_project.models.Klasa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KlasyService {
    public List<Klasa> getKlasy();
    Klasa findById(long id);
    void updateKlasa(long id, String nazwa);
}
