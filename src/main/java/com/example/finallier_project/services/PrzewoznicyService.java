package com.example.finallier_project.services;

import com.example.finallier_project.controllers.PrzewoznicyController;
import com.example.finallier_project.models.Przewoznik;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("przewoznicyService")
public interface PrzewoznicyService {
    List<Przewoznik> findAll();
    Przewoznik findById(long id);
    void updatePrzewoznik(long id,String nazwa);
}
