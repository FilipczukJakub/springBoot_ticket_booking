package com.example.finallier_project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "uzytkownicy")
@Getter
@Setter
@NoArgsConstructor
public class Uzytkownik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "imie",nullable = false)
    private String imie;

    @Column(name = "nazwisko",nullable = false)
    private String nazwisko;

    @Column(name = "nr_tel",nullable = false)
    private String nr_tel;

    private String username;

    private String password;

    @Transient
    private String passwordConfirm;

    private boolean enabled = false;

    @ManyToMany
    @JoinTable(name = "uzytkownicy_role",joinColumns = @JoinColumn(name = "uzytkownik_id"),inverseJoinColumns = @JoinColumn(name = "rola_id"))
    private Set<Rola> role;

    public Uzytkownik(String username){
        this(username,false);
    }
    public Uzytkownik(String username, boolean enabled){
        this.username = username;
        this.enabled = enabled;
    }
    public Uzytkownik(String username,String password,Set<Rola> role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
}