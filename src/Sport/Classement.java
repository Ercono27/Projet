package Sport;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe métier représentant le classement d'un coureur dans une course avec sa place et son gain
 * @author Séraphin Terlinck
 * @version 0.1
 */
public class Classement {

    /**
     * Identifiant unique du classement
     */
    private int idClassement;
    /**
     * Place du coureur dans la course
     */
    private int place;
    /**
     * Gain du coureur dans la course
     */
    private BigDecimal gain;
    /**
     *Coureur associé au classement
     */
    private Coureur cour;
    private Course course;

    /**
     * Constructeur par défaut de la classe Classement
     */
    public Classement() {
        place = 0; // Initialisation par défaut de la place à 0.
    }

    /**
     * Constructeur paramètré de la classe Classement
     * @param idClassement identifiant unique du classement
     * @param place place du coureur dans la course
     * @param gain gain du coureur dans la course
     * @param cour coureur associé au classement
     */
    public Classement(int idClassement, int place, BigDecimal gain, Coureur cour,Course course) {
        this.idClassement = idClassement;
        this.place = place;
        this.gain = gain;
        this.cour = cour;
        this.course = course;
    }

    /**
     * Obtient l'identifiant unique du classement
     * @return l'identifiant unique du classement
     */
    public int getIdClassement() {
        return idClassement;
    }

    /**
     * Définit l'identifiant unique du classement
     * @param idClassement nouvel identifiant unique du classement
     */
    public void setIdClassement(int idClassement) {
        this.idClassement = idClassement;
    }

    /**
     * Obtient la place du coureur dans la course
     * @return la place du coureur dans la course
     */
    public int getPlace() {
        return place;
    }

    /**
     * Définit la place du coureur dans la course
     * @param place nouvelle place du coureur
     */
    public void setPlace(int place) {
        this.place = place;
    }

    /**
     * Obtient le gain du coureur dans la course
     * @return le gain du coureur dans la course
     */
    public BigDecimal getGain() {
        return gain;
    }

    /**
     * Définit le gain du coureur dans la course
     * @param gain nouveau gain du coureur
     */
    public void setGain(BigDecimal gain) {
        this.gain = gain;
    }

    /**
     * Obtient le coureur associé au classement
     * @return le coureur associé au classement
     */
    public Coureur getCour() {
        return cour;
    }

    /**
     * Définit le coureur associé au classement
     * @param cour nouveau coureur associé au classement
     */
    public void setCour(Coureur cour) {
        this.cour = cour;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Redéfinition de la méthode equals pour comparer deux classements par leur identifiant unique
     * @param o l'objet à comparer
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classement that = (Classement) o;
        return idClassement == that.idClassement;
    }

    @Override
    public String toString() {
        return "Classement{" +
                "idClassement=" + idClassement +
                ", place=" + place +
                ", gain=" + gain +
                ", coureur=" + cour +
                ", course=" + course +
                '}';
    }
}
