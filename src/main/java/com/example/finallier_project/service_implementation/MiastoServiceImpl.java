package com.example.finallier_project.service_implementation;

import com.example.finallier_project.models.Miasto;
import com.example.finallier_project.models.Przewoznik;
import com.example.finallier_project.repositories.KrajRepository;
import com.example.finallier_project.repositories.MiastoRepository;
import com.example.finallier_project.services.MiastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Service("miastoService")
public class MiastoServiceImpl implements MiastoService {
    @Autowired
    MiastoRepository miastoRepository;
    @Autowired
    KrajRepository krajRepository;
    public List<Miasto> miasta(){
        return miastoRepository.findAll();
    }

    @Transactional
    public Miasto findMiasto(long index) {
        var miasto = miastoRepository.findById(index);
        if (miasto == null)
            return new Miasto();
        else
            return miasto;
    }

    @Override
    @Transactional
    public void updateMiasto(long id, String nazwa, long krajId) {

        if(id == 0){
            Miasto miasto = new Miasto();
            miasto.setNazwa(nazwa);
            miasto.setKraj(krajRepository.getReferenceById(krajId));
            if(validate(miasto))
                miastoRepository.save(miasto);
        }
        else {
            var miasto = miastoRepository.getReferenceById(id);
            miasto.setNazwa(nazwa);
            miasto.setKraj(krajRepository.getReferenceById(krajId));
        }
    }

    private boolean validate(Miasto miasto){
        var miasto2 = miastoRepository.findByNazwa(miasto.getNazwa());
        if (miasto2 == null){
            return true;
        }else
            return false;
    }
}
