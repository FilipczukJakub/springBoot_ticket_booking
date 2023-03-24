package com.example.finallier_project.services;

import com.example.finallier_project.models.Klasa;
import com.example.finallier_project.models.Lot;
import com.example.finallier_project.models.Miasto;
import com.example.finallier_project.models.Przewoznik;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public interface LotService {
    @Transactional
    public List<Lot> getLoty(Miasto zmiasto, Miasto domiasto);
    public List<Lot> getLoty(Miasto zmiasto, Miasto domiasto, LocalDate data);
    public List<Lot> getLoty();
    @Transactional
    public List<Przewoznik> getPrzewoznicy(Miasto zmiasto, Miasto domiasto);
    @Transactional
    public Lot getLotById(long id);
    void updateLot(long id,long zmiasto,long domiasto,long przewoznik,long klasa,String data,boolean od_okna);
}
