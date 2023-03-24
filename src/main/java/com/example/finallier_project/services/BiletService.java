package com.example.finallier_project.services;

import com.example.finallier_project.models.Bilet;
import com.example.finallier_project.models.Lot;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface BiletService {
    public float setCena(Lot lot);
    @Transactional
    public void zapisz(Bilet bilet);
    public List<Bilet> getBilety(String username);
}
