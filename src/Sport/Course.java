package Sport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Classe métier de gestion d'une course
 * @author Séraphin Terlinck
 * @version 0.1
 */
public class Course {
    /**
     * Identifiant unique de la course
     */
    private int idCourse;
    /**
     * Nom de la course
     */
    private String nom;
    /**
     * Cagnotte total de la course
     */
    private BigDecimal priceMoney;
    /**
     * Kilométrage de la course
     */
    private int km;
    /**
     * Liste des infos de la course
     */
    private List<Infos> listeInfo = new ArrayList<>();
    /**
     * Liste des classements de la course
     */
    private List<Classement> listeClassement = new ArrayList<>();

    /**
     * Constructeur par défaut de la classe Course
     */
    public Course() {
    }

    /**
     * Constructeur paramètré de la classe Course
     * @param idCourse identifiant unique de la course
     * @param nom nom de la course
     * @param priceMoney cagnotte total de la course
     * @param km kilométrage de la course
     */
    public Course(int idCourse, String nom, BigDecimal priceMoney, int km) {
        this.idCourse = idCourse;
        this.nom = nom;
        this.priceMoney = priceMoney;
        this.km = km;
    }

    /**
     * Obtient l'identifiant de la course
     * @return l'identifiant de la course
     */
    public int getIdCourse() {
        return idCourse;
    }

    /**
     * Définit l'identifiant de la course
     * @param idCourse nouvel identifiant de la course
     */
    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    /**
     * Obtient le nom de la course
     * @return le nom de la course
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la course
     * @param nom nouveau nom de la course
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient la récompense cagnotte totale de la course
     * @return la récompense cagnotte totale de la course
     */
    public BigDecimal getPriceMoney() {
        return priceMoney;
    }

    /**
     * Définit la cagnotte totale de la course.
     * @param priceMoney nouvelle cagnotte totale de la course.
     */
    public void setPriceMoney(BigDecimal priceMoney) {
        this.priceMoney = priceMoney;
    }

    /**
     * Obtient la distance de la course en kilomètres
     * @return la distance de la course
     */
    public int getKm() {
        return km;
    }

    /**
     * Définit la distance de la course en kilomètres
     * @param km nouvelle distance de la course
     */
    public void setKm(int km) {
        this.km = km;
    }

    /**
     * Obtient la liste d'informations associées à la course
     * @return la liste d'informations associées à la course
     */
    public List<Infos> getListeInfo() {
        return listeInfo;
    }

    /**
     * Obtient la liste de classements associés à la course.
     * @return la liste de classements associés à la course.
     */
    public List<Classement> getListeClassement() {
        return listeClassement;
    }
    /**
     * Méthode pour obtenir une liste de coureurs avec leur place et leur gain
     * @return la liste de coureurs avec leur place et leur gain
     */
    public List<CoureurPlaceGain> listeCoureurPlaceGain() {
        List<CoureurPlaceGain> listeCoureursPlaceGain = new ArrayList<>();
        int place;
        for (Classement classement : getListeClassement()) {
            place = classement.getPlace();
            BigDecimal gain = classement.getGain();
            CoureurPlaceGain cpg = new CoureurPlaceGain();
            listeCoureursPlaceGain.add(cpg);
        }
        return listeCoureursPlaceGain;
    }

    /**
     * Méthode pour calculer le gain total de la course
     * @return le gain total de la course
     */
    public BigDecimal gainTotal() {
        BigDecimal total = new BigDecimal(0);
        for (Classement c : listeClassement) {
            total = c.getGain().add(total);
        }
        return total;
    }

    /**
     * Méthode pour obtenir le vainqueur de la course.
     * @return le coureur vainqueur de la course.
     */
    public Coureur vainqueur() {
        Coureur tmp = new Coureur();
        for (Classement c : listeClassement) {
            if (c.getPlace() == 1)
                tmp = c.getCour();
        }
        return tmp;
    }

    /**
     * Méthode pour ajouter un coureur à la liste de classement de la course
     * @param c le coureur à ajouter
     */
    public void addCoureur(Coureur c) {
        Classement cl = new Classement();
        cl.setCour(c);
        listeClassement.add(cl);
    }

    /**
     * Méthode pour supprimer un coureur de la liste de classement de la course
     * @param c le coureur à supprimer
     */
    public void supCoureur(Coureur c) {
        listeClassement.removeIf(cl -> cl.getCour().equals(c));
    }

    /**
     * Méthode pour initialiser le classement et le gain d'un coureur.
     * @param c le coureur à mettre à jour
     * @param place la nouvelle place du coureur
     * @param gain le nouveau gain du coureur
     */
    public void resultat(Coureur c, int place, BigDecimal gain) {
        int x=listeClassement.indexOf(c);
        Classement cl=new Classement();
        cl=listeClassement.get(x);
        cl.setPlace(place);
        cl.setGain(gain);
        listeClassement.set(x,cl);
    }
    /**
     * Méthode pour modifier le classement et le gain d'un coureur déja classé
     * @param c le coureur à modifier
     * @param place la nouvelle place du coureur
     * @param gain le nouveau gain du coureur
     */
    public void modifier(Coureur c, int place, BigDecimal gain) {
        int x = listeClassement.indexOf(c);
        Classement cla = new Classement();
        if (x != -1)
        {
            cla.setCour(c);
            cla.setPlace(place);
            cla.setGain(gain);
            listeClassement.set(x, cla);
        } else {
            System.out.println("Ce coureur ne participe pas à la course.");
        }
    }

    /**
     * Méthode pour ajouter une ville aux informations associées à la course
     * @param ville la ville à ajouter
     */
    public void addVille(Ville ville) {
        Infos i = new Infos();
        LocalDate d = null;
        i.setVille(ville);
        i.setDepartDate(d);
        listeInfo.add(i);
    }

    /**
     * Modifie la date de départ associée à une ville spécifiée dans la liste d'informations
     * @param date2 nouvelle date de départ à assigner
     * @param ville nom de la ville dont la date de départ doit être modifiée
     */
    public void modifVille(LocalDate date2,String ville){
        for (Infos i : listeInfo){
            if(i.getVille().getNom().equals(ville)){
                i.setDepartDate(date2);
            }
        }
    }


    /**
     * Méthode pour supprimer une ville des informations associées à la course
     * @param v la ville à supprimer
     */
    public void supVille(Ville v) {
        listeInfo.removeIf(i -> i.getVille().equals(v));
    }

    /**
     * Méthode pour obtenir une liste de villes associées à la course
     * @return la liste de villes associées à la course
     */
    public List<Ville> listeVille() {
        List<Ville> listeVille = new ArrayList<>();
        for (Infos i : listeInfo) {
            listeVille.add(i.getVille());
        }
        return listeVille;
    }

    /**
     * Méthode pour vérifier si le classement de la course est complet
     * @return true si le classement est complet, false sinon
     */
    public boolean classementComplet() {
        int tmp;
        for (Classement c : listeClassement) {
            tmp = c.getPlace();
            if (tmp == 0)
                return false;
        }
        return true;
    }
}
