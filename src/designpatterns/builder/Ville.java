package designpatterns.builder;

import Sport.Coureur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ville {
    protected int idVille;
    protected String nom;
    protected double latitude;
    protected double longitude;
    protected String pays;
    protected List<Sport.Coureur> liCour = new ArrayList<>();

    public Ville(VilleBuilder vb) {
        this.idVille = vb.idVille;
        this.nom = vb.nom;
        this.latitude = vb.latitude;
        this.longitude = vb.longitude;
        this.pays = vb.pays;
    }
    public int getIdVille() {
        return idVille;
    }
    public String getNom() {
        return nom;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public String getPays() {
        return pays;
    }
    public List<Coureur> getLiCour() {
        return liCour;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return idVille == ville.idVille && Double.compare(latitude, ville.latitude) == 0 && Double.compare(longitude, ville.longitude) == 0 && Objects.equals(nom, ville.nom) && Objects.equals(pays, ville.pays);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idVille, nom, latitude, longitude, pays, liCour);
    }
    @Override
    public String toString() {
        return "Ville{" +
                "idVille=" + idVille +
                ", nom='" + nom + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", pays='" + pays + '\'' +
                ", liCour=" + liCour +
                '}';
    }

    public static class VilleBuilder{
        protected int idVille;
        protected String nom;
        protected double latitude;
        protected double longitude;
        protected String pays;

        public VilleBuilder setIdVille(int idVille) {
            this.idVille = idVille;
            return this;
        }

        public VilleBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public VilleBuilder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public VilleBuilder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public VilleBuilder setPays(String pays) {
            this.pays = pays;
            return this;
        }
        public Ville build() throws Exception {
            if (idVille <= 0 || nom == null || nom.isEmpty() || pays == null || pays.isEmpty()) {
                throw new Exception("Informations de construction incomplètes");
            }
            return new Ville(this);
        }

    }

}
