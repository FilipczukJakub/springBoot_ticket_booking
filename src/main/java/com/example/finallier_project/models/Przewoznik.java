package com.example.finallier_project.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "przewoznik")
@Getter
@Setter
@NoArgsConstructor
public class Przewoznik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nazwa",nullable = false)
    public String nazwa;
}
