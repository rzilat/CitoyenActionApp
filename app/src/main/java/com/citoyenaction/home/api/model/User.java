package com.citoyenaction.home.api.model;

public class User {
   private String email	;
   private String nom;
   private String password;
   private String prenom;
   private int userId;

    public User(String email, String nom, String password, String prenom, int userId) {
        this.email = email;
        this.nom = nom;
        this.password = password;
        this.prenom = prenom;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
