package com.example.finallier_project.service_implementation;

import com.example.finallier_project.models.Klasa;
import com.example.finallier_project.repositories.KlasaRepository;
import com.example.finallier_project.services.KlasyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KlasyServiceImpl implements KlasyService {
    @Autowired
    KlasaRepository klasaRepository;
    @Override
    @Transactional
    public List<Klasa> getKlasy() {
        return klasaRepository.findAll();
    }

    @Override
    public Klasa findById(long id) {
        if(id == -1){
            return new Klasa();
        }else {
            return klasaRepository.getReferenceById(id);
        }
    }

    @Override
    @Transactional
    public void updateKlasa(long id, String nazwa) {
        if(id == 0){
            Klasa klasa = new Klasa();
            klasa.setNazwa(nazwa);
            if(validate(klasa))
                klasaRepository.save(klasa);
        }else{
            Klasa klasa = klasaRepository.getReferenceById(id);
            klasa.setNazwa(nazwa);
        }
    }

    private boolean validate(Klasa klasa){
        var klasa2 = klasaRepository.findByNazwa(klasa.getNazwa());
        if (klasa2 == null){
            return true;
        }else
            return false;
    }
}
