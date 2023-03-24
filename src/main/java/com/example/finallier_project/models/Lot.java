package com.example.finallier_project.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "lot")
@Getter
@Setter
@NoArgsConstructor
public class Lot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zmiasto",nullable = false)
    @NotNull
    private Miasto zmiasto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "domiasto",nullable = false)
    @NotNull
    private Miasto domiasto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "klasa",nullable = false)
    private Klasa klasa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "przewoznik",nullable = false)
    @NotNull
    private Przewoznik przewoznik;

    @Column(name = "data",nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    @Column(name = "od_okna",nullable = false)
    @NotNull
    private boolean od_okna;
}

