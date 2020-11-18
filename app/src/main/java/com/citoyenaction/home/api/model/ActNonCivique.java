package com.citoyenaction.home.api.model;

import java.util.Date;

public class ActNonCivique {
    private long actNonCiviqueId;
    private String titre,description;
    private Date date;
    private int photo,video;
    private User user;

    public ActNonCivique(String titre, String description, Date date, int photo, int video, User user) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.photo = photo;
        this.video = video;
        this.user = user;
    }

    public ActNonCivique(long actNonCiviqueId) {
        this.actNonCiviqueId = actNonCiviqueId;
    }

    public long getActNonCiviqueId() {
        return actNonCiviqueId;
    }

    public void setActNonCiviqueId(long actNonCiviqueId) {
        this.actNonCiviqueId = actNonCiviqueId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }
}
