package Sport;

import java.util.Date;
import java.util.Objects;

public class Coureur {
    private int idCoureur;
    private String matricule;
    private String nom;
    private String prenom;
    private Date dateNaiss;
    private String nationalite;
    private Ville villeResidence;
    public Coureur(){
    }

    public int getIdCoureur() {
        return idCoureur;
    }

    public void setIdCoureur(int idCoureur) {
        this.idCoureur = idCoureur;
    }

    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matricule) {
        this.matricule = matricule;
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
    public Date getDateNaiss() {
        return dateNaiss;
    }
    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }
    public String getNationalite() {
        return nationalite;
    }
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }
    public Ville getVilleResidence() {
        return villeResidence;
    }
    public void setVilleResidence(Ville villeResidence) {
        this.villeResidence = villeResidence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coureur coureur = (Coureur) o;
        return idCoureur == coureur.idCoureur;
    }

}
