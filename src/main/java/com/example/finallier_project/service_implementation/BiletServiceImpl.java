package com.example.finallier_project.service_implementation;

import com.example.finallier_project.models.Bilet;
import com.example.finallier_project.models.Lot;
import com.example.finallier_project.repositories.BiletRepository;
import com.example.finallier_project.services.BiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BiletServiceImpl implements BiletService {
    @Autowired
    BiletRepository biletRepository;
    @Override
    public float setCena(Lot lot) {
        if (lot.getKlasa().getNazwa().equals("VIP")){
            if (lot.isOd_okna()){
                return 2500.0f;
            }else {
                return 2000.0f;
            }
        }
        else if(lot.getKlasa().getNazwa().equals("First Class")){
            if (lot.isOd_okna()){
                return 1799.99f;
            }else {
                return 1499.99f;
            }
        }
        else if(lot.getKlasa().getNazwa().equals("Business")){
            if (lot.isOd_okna()){
                return 1099.99f;
            }else {
                return 999.99f;
            }
        }
        else{
            if (lot.isOd_okna()){
                return 549.99f;
            }else {
                return 499.99f;
            }
        }
    }

    @Override
    @Transactional
    public void zapisz(Bilet bilet) {
        biletRepository.save(bilet);
    }

    @Override
    @Transactional
    public List<Bilet> getBilety(String username) {
        return biletRepository.findAllByUzytkownikUsername(username);
    }
}
