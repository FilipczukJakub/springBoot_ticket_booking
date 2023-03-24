package com.example.finallier_project.service_implementation;

import com.example.finallier_project.models.Klasa;
import com.example.finallier_project.models.Kraj;
import com.example.finallier_project.repositories.KrajRepository;
import com.example.finallier_project.repositories.MiastoRepository;
import com.example.finallier_project.services.KrajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KrajServiceImpl implements KrajService {
    @Autowired
    KrajRepository krajRepository;
    @Autowired
    MiastoRepository miastoRepository;
    @Override
    public List<Kraj> findAll() {
        return krajRepository.findAll();
    }

    @Override
    public Kraj findKraj(long id) {
        if(id == -1){
            return new Kraj();
        }
        else {
            return krajRepository.getReferenceById(id);
        }
    }

    @Override
    @Transactional
    public void updateKraj(long id, String nazwa) {
        if(id == 0){
            Kraj kraj = new Kraj();
            kraj.setNazwa(nazwa);
            if (validate(kraj))
                krajRepository.save(kraj);
        }else{
            Kraj kraj = krajRepository.getReferenceById(id);
            kraj.setNazwa(nazwa);
        }
    }

    private boolean validate(Kraj kraj){
        var kraj2 = krajRepository.findByNazwa(kraj.getNazwa());
        if (kraj2 == null){
            return true;
        }else
            return false;
    }
}
