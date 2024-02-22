package Sport;

import java.math.BigDecimal;

/**
 * Classe représentant les informations sur la place et le gain d'un coureur dans une course.
 * @author Séraphin Terlinck
 * @version 0.1
 */
public class CoureurPlaceGain {

    /**
     * Coureur associé
     */
    private Coureur coureur;
    /**
     * Place du coureur dans la course
     */
    private int place;
    /**
     * Gain du coureur
     */
    private BigDecimal gain;

    /**
     * Constructeur par défaut de la classe CoureurPlaceGain
     */
    public CoureurPlaceGain() {
    }

    /**
     * Constructeur paramètré de la classe CoureurPlaceGain
     * @param coureur coureur associé.
     * @param gain gain du coureur dans la course.
     * @param place place du coureur dans la course.
     */
    public CoureurPlaceGain(Coureur coureur, BigDecimal gain, int place) {
        this.coureur = coureur;
        this.gain = gain;
        this.place = place;
    }

    /**
     * Obtient le coureur associé
     * @return le coureur associé
     */
    public Coureur getCoureur() {
        return coureur;
    }

    /**
     * Définit le coureur associé
     * @param coureur nouveau coureur associé
     */
    public void setCoureur(Coureur coureur) {
        this.coureur = coureur;
    }

    /**
     * Obtient la place du coureur dans la course.
     * @return la place du coureur dans la course.
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
}
