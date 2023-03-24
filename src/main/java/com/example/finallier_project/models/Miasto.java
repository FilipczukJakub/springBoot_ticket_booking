package com.example.finallier_project.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Entity
@Table(name = "miasto")
@Getter
@Setter
@NoArgsConstructor
public class Miasto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nazwa",nullable = false)
    @NotNull
    private String nazwa;

    @ManyToOne(fetch = FetchType.EAGER)
    private Kraj kraj;
}