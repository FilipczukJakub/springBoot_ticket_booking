package com.example.finallier_project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name="role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Types type;

    @ManyToMany(mappedBy = "role")
    private Set<Uzytkownik> uzytkownicy;

    public Rola(Types type){
        this.type = type;
    }

    public static enum Types{
        ROLE_ADMIN,
        ROLE_USER
    }
}
