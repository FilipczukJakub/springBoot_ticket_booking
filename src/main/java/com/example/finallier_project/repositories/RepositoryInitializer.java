package com.example.finallier_project.repositories;

import com.example.finallier_project.models.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class RepositoryInitializer{
    @Autowired
    private BiletRepository biletRepository;
    @Autowired
    private KlasaRepository klasaRepository;
    @Autowired
    private LotRepository lotRepository;
    @Autowired
    private MiastoRepository miastoRepository;
    @Autowired
    private PrzewoznikRepository przewoznikRepository;
    @Autowired
    private UzytkownikRepository uzytkownikRepository;
    @Autowired
    private RolaRepository rolaRepository;
    @Autowired
    private KrajRepository krajRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Bean
    InitializingBean init(){
        return () -> {
            if (rolaRepository.findAll().isEmpty()){
                rolaRepository.save(new Rola(Rola.Types.ROLE_USER));
                rolaRepository.save(new Rola(Rola.Types.ROLE_ADMIN));
            }
            if (klasaRepository.findAll().isEmpty()){
                Klasa klasa1 = new Klasa();
                klasa1.setNazwa("VIP");
                Klasa klasa2 = new Klasa();
                klasa2.setNazwa("First Class");
                Klasa klasa3 = new Klasa();
                klasa3.setNazwa("Business");
                Klasa klasa4 = new Klasa();
                klasa4.setNazwa("Economy");
                klasaRepository.save(klasa1);
                klasaRepository.save(klasa2);
                klasaRepository.save(klasa3);
                klasaRepository.save(klasa4);
            }
            if(krajRepository.findAll().isEmpty()){
                Kraj kraj = new Kraj();
                kraj.setNazwa("Polska");
                Kraj kraj2 = new Kraj();
                kraj2.setNazwa("U.S.A");
                Kraj kraj3 = new Kraj();
                kraj3.setNazwa("Katalonia");
                Kraj kraj4 = new Kraj();
                kraj4.setNazwa("Chiny");
                krajRepository.save(kraj);
                krajRepository.save(kraj2);
                krajRepository.save(kraj3);
                krajRepository.save(kraj4);
            }
            if (miastoRepository.findAll().isEmpty()){
                Miasto miasto1 = new Miasto();
                miasto1.setNazwa("Warszawa");
                miasto1.setKraj(krajRepository.getReferenceById(1l));
                Miasto miasto2 = new Miasto();
                miasto2.setNazwa("Nowy York");
                miasto2.setKraj(krajRepository.getReferenceById(2l));
                Miasto miasto3 = new Miasto();
                miasto3.setNazwa("Barcelona");
                miasto3.setKraj(krajRepository.getReferenceById(3l));
                Miasto miasto4 = new Miasto();
                miasto4.setNazwa("Hongkong");
                miasto4.setKraj(krajRepository.getReferenceById(4l));
                Miasto miasto5 = new Miasto();
                miasto5.setNazwa("Pekin");
                miasto5.setKraj(krajRepository.getReferenceById(4l));
                miastoRepository.save(miasto1);
                miastoRepository.save(miasto2);
                miastoRepository.save(miasto3);
                miastoRepository.save(miasto4);
                miastoRepository.save(miasto5);
            }
            if (przewoznikRepository.findAll().isEmpty()){
                Przewoznik przewoznik1 = new Przewoznik();
                przewoznik1.setNazwa("Corendon");
                Przewoznik przewoznik2 = new Przewoznik();
                przewoznik2.setNazwa("LOT");
                Przewoznik przewoznik3 = new Przewoznik();
                przewoznik3.setNazwa("Ryanair");
                przewoznikRepository.save(przewoznik1);
                przewoznikRepository.save(przewoznik2);
                przewoznikRepository.save(przewoznik3);
            }
            if (uzytkownikRepository.findAll().isEmpty()){
                Uzytkownik uzytkownik1 = new Uzytkownik("user",true);
                uzytkownik1.setImie("Jan");
                uzytkownik1.setNazwisko("Kowalski");
                uzytkownik1.setNr_tel("111111111");
                uzytkownik1.setEmail("email1@gmail.com");
                uzytkownik1.setRole(new HashSet<>(Arrays.asList(rolaRepository.getReferenceById(1l))));
                uzytkownik1.setPassword(passwordEncoder.encode("user"));
                uzytkownik1.setUsername("user");
                uzytkownik1.setEnabled(false);
                Uzytkownik uzytkownik2 = new Uzytkownik();
                uzytkownik2.setImie("Michał");
                uzytkownik2.setNazwisko("Michalak");
                uzytkownik2.setNr_tel("222222222");
                uzytkownik2.setEmail("email2@gmail.com");
                uzytkownik2.setRole(new HashSet<>(Arrays.asList(rolaRepository.getReferenceById(2l))));
                uzytkownik2.setPassword(passwordEncoder.encode("admin"));
                uzytkownik2.setUsername("admin");
                uzytkownik2.setEnabled(true);
                Uzytkownik uzytkownik3 = new Uzytkownik();
                uzytkownik3.setImie("Aleksandra");
                uzytkownik3.setNazwisko("Górska");
                uzytkownik3.setNr_tel("333333333");
                uzytkownik3.setEmail("email3@gmail.com");
                uzytkownik3.setRole(new HashSet<>(Arrays.asList(rolaRepository.getReferenceById(1l),rolaRepository.getReferenceById(2l))));
                uzytkownik3.setPassword(passwordEncoder.encode("superuser"));
                uzytkownik3.setUsername("superuser");
                uzytkownik3.setEnabled(true);
                uzytkownikRepository.save(uzytkownik1);
                uzytkownikRepository.save(uzytkownik2);
                uzytkownikRepository.save(uzytkownik3);
            }
            if (lotRepository.findAll().isEmpty()){
                Klasa klasa1 = klasaRepository.getReferenceById(1l);
                Klasa klasa2 = klasaRepository.getReferenceById(2l);
                Klasa klasa3 = klasaRepository.getReferenceById(3l);
                Klasa klasa4 = klasaRepository.getReferenceById(4l);
                LocalDate data = LocalDate.of(2023,01,25);

                Lot lot = new Lot();
                lot.setZmiasto(miastoRepository.getReferenceById(3l));
                lot.setDomiasto(miastoRepository.getReferenceById(2l));
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setData(data);
                lot.setKlasa(klasa1);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setZmiasto(miastoRepository.getReferenceById(3l));
                lot.setDomiasto(miastoRepository.getReferenceById(2l));
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setData(data);
                lot.setKlasa(klasa2);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setZmiasto(miastoRepository.getReferenceById(3l));
                lot.setDomiasto(miastoRepository.getReferenceById(2l));
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setData(data);
                lot.setKlasa(klasa3);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setZmiasto(miastoRepository.getReferenceById(3l));
                lot.setDomiasto(miastoRepository.getReferenceById(2l));
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setData(data);
                lot.setKlasa(klasa4);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(2l));
                lot.setZmiasto(miastoRepository.getReferenceById(5l));
                lot.setDomiasto(miastoRepository.getReferenceById(1l));
                lot.setKlasa(klasa1);
                lot.setData(data);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(2l));
                lot.setZmiasto(miastoRepository.getReferenceById(5l));
                lot.setDomiasto(miastoRepository.getReferenceById(1l));
                lot.setKlasa(klasa2);
                lot.setData(data);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(3l));
                lot.setZmiasto(miastoRepository.getReferenceById(4l));
                lot.setDomiasto(miastoRepository.getReferenceById(2l));
                lot.setKlasa(klasa1);
                lot.setData(data);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(3l));
                lot.setZmiasto(miastoRepository.getReferenceById(4l));
                lot.setDomiasto(miastoRepository.getReferenceById(2l));
                lot.setKlasa(klasa2);
                lot.setData(data);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(3l));
                lot.setZmiasto(miastoRepository.getReferenceById(4l));
                lot.setDomiasto(miastoRepository.getReferenceById(2l));
                lot.setKlasa(klasa3);
                lot.setData(data);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setZmiasto(miastoRepository.getReferenceById(2l));
                lot.setDomiasto(miastoRepository.getReferenceById(1l));
                lot.setKlasa(klasa1);
                lot.setData(data);
                lot.setOd_okna(true);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setZmiasto(miastoRepository.getReferenceById(2l));
                lot.setDomiasto(miastoRepository.getReferenceById(1l));
                lot.setKlasa(klasa2);
                lot.setData(data);
                lot.setOd_okna(true);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setZmiasto(miastoRepository.getReferenceById(2l));
                lot.setDomiasto(miastoRepository.getReferenceById(1l));
                lot.setKlasa(klasa3);
                lot.setData(data);
                lot.setOd_okna(true);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setZmiasto(miastoRepository.getReferenceById(2l));
                lot.setDomiasto(miastoRepository.getReferenceById(1l));
                lot.setKlasa(klasa4);
                lot.setData(data);
                lot.setOd_okna(true);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setZmiasto(miastoRepository.getReferenceById(2l));
                lot.setDomiasto(miastoRepository.getReferenceById(1l));
                lot.setKlasa(klasa1);
                lot.setData(data);
                lot.setOd_okna(false);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setZmiasto(miastoRepository.getReferenceById(2l));
                lot.setDomiasto(miastoRepository.getReferenceById(1l));
                lot.setKlasa(klasa2);
                lot.setData(data);
                lot.setOd_okna(false);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setZmiasto(miastoRepository.getReferenceById(2l));
                lot.setDomiasto(miastoRepository.getReferenceById(1l));
                lot.setKlasa(klasa3);
                lot.setData(data);
                lot.setOd_okna(false);
                lotRepository.save(lot);

                lot = new Lot();
                lot.setPrzewoznik(przewoznikRepository.getReferenceById(1l));
                lot.setZmiasto(miastoRepository.getReferenceById(2l));
                lot.setDomiasto(miastoRepository.getReferenceById(1l));
                lot.setKlasa(klasa4);
                lot.setData(data);
                lot.setOd_okna(false);
                lotRepository.save(lot);
            }
            if (biletRepository.findAll().isEmpty()){
                Bilet bilet = new Bilet();
                bilet.setUzytkownik(uzytkownikRepository.findByUsername("user"));
                bilet.setCena(2500.0f);
                bilet.setLot(lotRepository.findById(7l));
                biletRepository.save(bilet);
            }

        };
    }
}
