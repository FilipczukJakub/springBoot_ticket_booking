package com.example.finallier_project.service_implementation;

import com.example.finallier_project.models.Klasa;
import com.example.finallier_project.models.Lot;
import com.example.finallier_project.models.Miasto;
import com.example.finallier_project.models.Przewoznik;
import com.example.finallier_project.repositories.KlasaRepository;
import com.example.finallier_project.repositories.LotRepository;
import com.example.finallier_project.repositories.MiastoRepository;
import com.example.finallier_project.repositories.PrzewoznikRepository;
import com.example.finallier_project.services.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class LotServiceImpl implements LotService {
    @Autowired
    LotRepository lotRepository;
    @Autowired
    KlasaRepository klasaRepository;
    @Autowired
    MiastoRepository miastoRepository;
    @Autowired
    PrzewoznikRepository przewoznikRepository;

    @Transactional
    @Override
    public List<Lot> getLoty(Miasto zmiasto, Miasto domiasto){
        return lotRepository.findAllByZmiastoAndDomiasto(zmiasto,domiasto);
    }

    @Override
    public List<Lot> getLoty(Miasto zmiasto, Miasto domiasto, LocalDate data) {
        return lotRepository.findByZmiastoAndDomiastoAndData(zmiasto,domiasto,data);
    }

    @Override
    public List<Lot> getLoty() {
        return lotRepository.findAll();
    }

    @Transactional
    @Override
    public List<Przewoznik> getPrzewoznicy(Miasto zmiasto, Miasto domiasto) {
        var listaLot = getLoty(zmiasto,domiasto);
        List<Przewoznik> listaPrzewoznik = new LinkedList<>();
        for (var i=0;i<listaLot.size();i++){
            listaPrzewoznik.add(listaLot.get(i).getPrzewoznik());
        }
        return listaPrzewoznik;
    }

    @Override
    public Lot getLotById(long id) {
        if(id == -1){
            return new Lot();
        }else
            return lotRepository.findById(id);
    }

    @Override
    public void updateLot(long id, long zmiasto, long domiasto, long przewoznik, long klasa, String data, boolean od_okna) {
        if(id == 0){
            Lot lot = new Lot();
            lot.setZmiasto(miastoRepository.getReferenceById(zmiasto));
            lot.setDomiasto(miastoRepository.getReferenceById(domiasto));
            lot.setPrzewoznik(przewoznikRepository.getReferenceById(przewoznik));
            lot.setKlasa(klasaRepository.getReferenceById(klasa));
            lot.setData(LocalDate.parse(data));
            lot.setOd_okna(od_okna);
            lotRepository.save(lot);
        }else{
            Lot lot = lotRepository.getReferenceById(id);
            lot.setZmiasto(miastoRepository.getReferenceById(zmiasto));
            lot.setDomiasto(miastoRepository.getReferenceById(domiasto));
            lot.setPrzewoznik(przewoznikRepository.getReferenceById(przewoznik));
            lot.setKlasa(klasaRepository.getReferenceById(klasa));
            lot.setData(LocalDate.parse(data));
            lot.setOd_okna(od_okna);
        }
    }

}
