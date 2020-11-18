package com.citoyenaction.home.api.model;

import java.util.Date;

public class Reaction {
    private long reactionId;
    private String titre,commentaire,evaluation;
    private Date date;
    private User user;
    private ActNonCivique actNonCivique;

    public Reaction(String titre, String commentaire, String evaluation, Date date, User user, ActNonCivique actNonCivique) {
        this.titre = titre;
        this.commentaire = commentaire;
        this.evaluation = evaluation;
        this.date = date;
        this.user = user;
        this.actNonCivique = actNonCivique;
    }

    public long getReactionId() {
        return reactionId;
    }

    public void setReactionId(long reactionId) {
        this.reactionId = reactionId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActNonCivique getActNonCivique() {
        return actNonCivique;
    }

    public void setActNonCivique(ActNonCivique actNonCivique) {
        this.actNonCivique = actNonCivique;
    }
}
