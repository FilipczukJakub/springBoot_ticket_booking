package com.example.finallier_project.services;

import com.example.finallier_project.models.Kraj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KrajService {
    public List<Kraj> findAll();
    Kraj findKraj(long id);
    void updateKraj(long id,String nazwa);
}
