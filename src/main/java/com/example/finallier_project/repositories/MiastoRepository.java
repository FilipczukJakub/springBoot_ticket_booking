package com.example.finallier_project.repositories;
import com.example.finallier_project.models.Miasto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MiastoRepository extends JpaRepository<Miasto,Long> {
    Miasto findById(long index);
    List<Miasto> findAllByKrajId(long id);
    Miasto findByNazwa(String nazwa);
}
