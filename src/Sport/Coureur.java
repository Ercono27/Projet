package Sport;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Classe métier de gestion d'un coureur
 * @author Séraphin Terlinck
 * @version 0.1
 */
public class Coureur {

    /**
     * Identifiant unique du coureur
     */
    private int idCoureur;
    /**
     * Matricule du coureur
     */
    private String matricule;
    /**
     * Nom du coureur
     */
    private String nom;
    /**
     * Prenom du coureur
     */
    private String prenom;
    /**
     * Date de naissance du coureur
     */
    private LocalDate dateNaiss;
    /**
     * Nationnalité du coureur
     */
    private String nationalite;
    /**
     * Ville de résidence du coureur
     */
    private Ville villeResidence;

    /**
     * Constructeur par défaut de la classe Coureur
     */
    public Coureur() {
    }

    /**
     * Constructeur paramètré de la classe Coureur
     *
     * @param idCoureur identifiant unique du coureur
     * @param matricule matricule du coureur
     * @param nom nom du coureur
     * @param prenom prénom du coureur
     * @param dateNaiss date de naissance du coureur
     * @param nationalite nationalité du coureur
     * @param villeResidence ville de résidence du coureur
     */
    public Coureur(int idCoureur, String matricule, String nom, String prenom, LocalDate dateNaiss, String nationalite, Ville villeResidence) {
        this.idCoureur = idCoureur;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.nationalite = nationalite;
        this.villeResidence = villeResidence;
    }

    /**
     * Obtient l'identifiant du coureur
     * @return l'identifiant du coureur
     */
    public int getIdCoureur() {
        return idCoureur;
    }

    /**
     * Définit l'identifiant du coureur
     * @param idCoureur nouvel identifiant du coureur
     */
    public void setIdCoureur(int idCoureur) {
        this.idCoureur = idCoureur;
    }

    /**
     * Obtient le matricule du coureur
     * @return le matricule du coureur
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Définit le matricule du coureur
     * @param matricule nouveau matricule du coureur
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Obtient le nom du coureur
     * @return le nom du coureur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du coureur
     * @param nom nouveau nom du coureur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le prénom du coureur
     * @return le prénom du coureur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du coureur
     * @param prenom nouveau prénom du coureur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Obtient la date de naissance du coureur
     * @return la date de naissance du coureur
     */
    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    /**
     * Définit la date de naissance du coureur
     * @param dateNaiss nouvelle date de naissance du coureur
     */
    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    /**
     * Obtient la nationalité du coureur
     * @return la nationalité du coureur
     */
    public String getNationalite() {
        return nationalite;
    }

    /**
     * Définit la nationalité du coureur
     * @param nationalite nouvelle nationalité du coureur
     */
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    /**
     * Obtient la ville de résidence du coureur
     * @return la ville de résidence du coureur
     */
    public Ville getVilleResidence() {
        return villeResidence;
    }

    /**
     * Définit la ville de résidence du coureur
     * @param villeResidence nouvelle ville de résidence du coureur
     */
    public void setVilleResidence(Ville villeResidence) {
        this.villeResidence = villeResidence;
    }

    /**
     * Redéfinition de la méthode equals pour comparer les coureurs avec leur identifiant
     * @param o l'objet à comparer avec le coureur
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coureur coureur = (Coureur) o;
        return idCoureur == coureur.idCoureur;
    }
    @Override
    public String toString() {
        return "Coureur{" +
                "idCoureur=" + idCoureur +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaiss=" + dateNaiss +
                ", nationalite='" + nationalite + '\'' +
                ", villeResidence=" + villeResidence +
                '}';
    }
}
