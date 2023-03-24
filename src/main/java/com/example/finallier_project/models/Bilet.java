package com.example.finallier_project.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "bilety")
@Log4j2
@AllArgsConstructor
@Getter
@Setter
public class Bilet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cena",nullable = false)
    @NonNull
    @Min(50)
    @Max(3000)
    private float cena;

    @JoinColumn(name = "lot",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Lot lot;


    @JoinColumn(name = "uzytkownik",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Uzytkownik uzytkownik;
    public Bilet(){}
}
