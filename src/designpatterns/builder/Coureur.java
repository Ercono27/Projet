package designpatterns.builder;

import Sport.Course;
import Sport.Ville;

import java.time.LocalDate;
import java.util.Objects;

public class Coureur {
    protected int idCoureur;

    protected String matricule;

    protected String nom;

    protected String prenom;

    protected LocalDate dateNaiss;

    protected String nationalite;

    protected Ville villeResidence;

    private Coureur(CoureurBuilder cb){
        this.idCoureur = cb.idCoureur;
        this.matricule = cb.matricule;
        this.nom = cb.nom;
        this.prenom = cb.prenom;
        this.dateNaiss = cb.dateNaiss;
        this.nationalite = cb.nationalite;
        this.villeResidence = cb.villeResidence;
    }

    public int getIdCoureur() {
        return idCoureur;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public String getNationalite() {
        return nationalite;
    }

    public Ville getVilleResidence() {
        return villeResidence;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coureur coureur = (Coureur) o;
        return idCoureur == coureur.idCoureur && Objects.equals(matricule, coureur.matricule) && Objects.equals(nom, coureur.nom) && Objects.equals(prenom, coureur.prenom) && Objects.equals(dateNaiss, coureur.dateNaiss) && Objects.equals(nationalite, coureur.nationalite) && Objects.equals(villeResidence, coureur.villeResidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCoureur, matricule, nom, prenom, dateNaiss, nationalite, villeResidence);
    }

    public static class CoureurBuilder{
        protected int idCoureur;
        protected String matricule;
        protected String nom;
        protected String prenom;
        protected LocalDate dateNaiss;
        protected String nationalite;
        protected Ville villeResidence;
        public CoureurBuilder setIdCoureur(int idCoureur) {
            this.idCoureur = idCoureur;
            return this;
        }
        public CoureurBuilder setMatricule(String matricule) {
            this.matricule = matricule;
            return this;
        }
        public CoureurBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }
        public CoureurBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }
        public CoureurBuilder setDateNaiss(LocalDate dateNaiss) {
            this.dateNaiss = dateNaiss;
            return this;
        }
        public CoureurBuilder setNationalite(String nationalite) {
            this.nationalite = nationalite;
            return this;
        }
        public CoureurBuilder setVilleResidence(Ville villeResidence) {
            this.villeResidence = villeResidence;
            return this;
        }
        public Coureur build() throws Exception {
            if (idCoureur <= 0 || matricule == null || nom == null || prenom == null || dateNaiss == null || nationalite == null || villeResidence == null) {
                throw new Exception("Informations de construction incomplètes");
            }
            return new Coureur(this);
        }
    }
}
