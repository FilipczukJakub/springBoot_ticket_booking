package com.example.finallier_project.services;

import com.example.finallier_project.models.Miasto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("miastoService")
public interface MiastoService {
    public List<Miasto> miasta();
    @Transactional
    Miasto findMiasto(long index);
    @Transactional
    void updateMiasto(long id,String nazwa,long krajId);
}
