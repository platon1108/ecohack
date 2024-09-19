package com.example.ecthone.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Entity
@Table( name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private int id;
    @Column(name = "fio")
    @Setter
    @Getter
    private String fio;
    @Column(name = "email")
    @Setter
    @Getter
    private String email=" ";
    @Column(name = "phone")
    @Setter
    @Getter
    private String phone=" ";
    @Column(name = "role")
    @Setter
    @Getter
    private Role role;
    @Column(name = "verified")
    @Setter
    @Getter
    private Boolean verification;
    @Column(name = "password")
    @Setter
    @Getter
    private String password;

    public Person(String fio, String email, String phone, Role role, Boolean verification,String password) {
        this.fio = fio;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.verification = verification;
        this.password = password;
    }

    public Person() {

    }
}
