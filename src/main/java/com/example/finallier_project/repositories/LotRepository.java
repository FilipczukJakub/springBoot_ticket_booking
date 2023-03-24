package com.example.finallier_project.repositories;
import com.example.finallier_project.models.Klasa;
import com.example.finallier_project.models.Lot;
import com.example.finallier_project.models.Miasto;
import com.example.finallier_project.models.Przewoznik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface LotRepository extends JpaRepository<Lot,Long> {
    public List<Lot> findAllByZmiastoAndDomiasto(Miasto zmiasto, Miasto domiasto);
    public List<Lot> findByZmiastoAndDomiastoAndData(Miasto zmiasto, Miasto domiasto, LocalDate data);
    public Lot findById(long id);
}
