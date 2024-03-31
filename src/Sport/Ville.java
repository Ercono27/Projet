package Sport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe métier de gestion d'une ville
 * @author Séraphin Terlinck
 * @version 0.1
 */
public class Ville {

    /**
     * Identifiant unique de la ville
     */
    private int idVille;
    /**
     * Nom de la ville
     */
    private String nom;
    /**
     * Latitude de la ville
     */
    private double latitude;
    /**
     * Longitude de la ville
     */
    private double longitude;
    /**
     * Pays au quel appartient la ville
     */
    private String pays;
    /**
     * Liste des coureur provenant de la ville
     */
    private List<Coureur> liCour = new ArrayList<>();

    /**
     * Constructeur par défaut de la classe Ville
     */
    public Ville() {
    }

    /**
     * Constructeur paramètré de la classe Ville
     * @param idVille identifiant unique de la ville
     * @param nom nom de la ville
     * @param latitude latitude géographique de la ville
     * @param longitude longitude géographique de la ville
     * @param pays pays auquel appartient la ville
     * @param liCour liste de coureurs provenant de ville
     */
    public Ville(int idVille, String nom, double latitude, double longitude, String pays, List<Coureur> liCour) {
        this.idVille = idVille;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pays = pays;
        this.liCour = liCour;
    }

    public Ville(int idVille, String nom, double latitude, double longitude, String pays) {
        this.idVille = idVille;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pays = pays;
    }

    public Ville(String nom, double latitude, double longitude, String pays) {
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pays = pays;
    }

    /**
     * Obtient l'identifiant de la ville
     * @return l'identifiant de la ville
     */
    public int getIdVille() {
        return idVille;
    }

    /**
     * Définit l'identifiant de la ville
     * @param idVille nouvel identifiant de la ville
     */
    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    /**
     * Obtient le nom de la ville
     * @return le nom de la ville
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la ville
     * @param nom nouveau nom de la ville
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient la latitude de la ville
     * @return la latitude de la ville
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Définit la latitude de la ville
     * @param latitude nouvelle latitude de la ville
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Obtient la longitude de la ville
     * @return la longitude de la ville
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Définit la longitude de la ville
     * @param longitude nouvelle longitude de la ville
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Obtient le pays de la ville
     * @return le pays de la ville
     */
    public String getPays() {
        return pays;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "idVille=" + idVille +
                ", nom='" + nom + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", pays='" + pays + '\'' +
                '}';
    }

    /**
     * Définit le pays de la ville
     * @param pays nouveau pays de la ville
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * Redéfinition de la méthode equals pour comparer les villes par leur identifiant
     * @param o l'objet à comparer avec la ville
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return idVille == ville.idVille;
    }

}
