package com.citoyenaction.home.api.model;

import java.util.Date;

public class User {
   private long userId;
   private String nom;
    private String prenom;
    private String email;
    private String password;
    private Date date;


    public User(String nom, String prenom, String email, String password, Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.date = date;
    }

    public User(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
