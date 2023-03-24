package com.example.finallier_project.service_implementation;

import com.example.finallier_project.models.Kraj;
import com.example.finallier_project.models.Przewoznik;
import com.example.finallier_project.repositories.PrzewoznikRepository;
import com.example.finallier_project.services.PrzewoznicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("przewoznicyService")
public class PrzewoznicyServiceImpl implements PrzewoznicyService {
    @Autowired
    PrzewoznikRepository przewoznikRepository;
    @Override
    public List<Przewoznik> findAll() {
        return przewoznikRepository.findAll();
    }

    @Override
    public Przewoznik findById(long id) {
        if(id == -1)
            return new Przewoznik();
        else
            return przewoznikRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public void updatePrzewoznik(long id, String nazwa) {
        if(id == 0){
            Przewoznik przewoznik = new Przewoznik();
            przewoznik.setNazwa(nazwa);
            if(validate(przewoznik))
                przewoznikRepository.save(przewoznik);
        }else{
            Przewoznik przewoznik = przewoznikRepository.getReferenceById(id);
            przewoznik.setNazwa(nazwa);
        }
    }

    private boolean validate(Przewoznik przewoznik){
        var przewoznik2 = przewoznikRepository.findByNazwa(przewoznik.getNazwa());
        if (przewoznik2 == null){
            return true;
        }else
            return false;
    }
}
