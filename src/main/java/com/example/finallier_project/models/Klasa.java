package com.example.finallier_project.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
@Entity
@Table(name = "klasa")
@Getter
@Setter
@NoArgsConstructor
public class Klasa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nazwa",nullable = false)
    @NotNull
    private String nazwa;
}
