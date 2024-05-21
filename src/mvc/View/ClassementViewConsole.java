package mvc.View;

import Sport.*;
import mvc.Controller.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.math.*;

import static utilitaires.Utilitaire.*;

public class ClassementViewConsole extends ClassementAbstractView {
    private Scanner sc=new Scanner(System.in);
    private List<Classement> lc;
    private CoureurController coureurController;
    private CourseController courseController;
    public ClassementViewConsole(CoureurController coureurController,CourseController courseController) {
        this.coureurController = coureurController;
        this.courseController=courseController;
    }
    public ClassementViewConsole(){}
    @Override
    public void affMsg(String msg) {
        System.out.println("Information: " + msg);
    }

    @Override
    public void affList(List classement) {
        affListe(classement);
    }

    public void menu() {
        update(classementController.getAll());
        do {
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));

            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }
    private void ajouter() {
        System.out.print("Place: ");
        int place = Integer.parseInt(sc.nextLine());
        System.out.print("Gain: ");
        BigDecimal gain = new BigDecimal(sc.nextLine());
        Coureur coureur = selectionnerCoureur();
        Course course = selectionnerCourse();
        Classement classement = new Classement(0, place, gain, coureur, course);
        Classement nouveauClassement = classementController.addClassement(classement);
        if (nouveauClassement != null) affMsg("Création de : " + nouveauClassement);
        else affMsg("Erreur de création");
    }
    private void retirer() {
        int nl = choixElt(lc);
        Classement classement = lc.get(nl - 1);
        boolean ok = classementController.removeClassement(classement);
        if (ok) affMsg("Classement supprimé");
        else affMsg("Classement non supprimé");
    }
    private void rechercher() {
        System.out.print("ID Classement : ");
        int idClassement = sc.nextInt();
        sc.nextLine();
        Classement classement = classementController.getClassementById(idClassement);
        if (classement != null) affMsg("Classement trouvé : " + classement);
        else affMsg("Aucun classement trouvé avec cet ID");
    }

    private void modifier() {
        int nl = choixElt(lc);
        Classement classement = lc.get(nl - 1);
        int place = Integer.parseInt(modifyIfNotBlank("Place: ", "" + classement.getPlace()));
        BigDecimal gain = new BigDecimal(modifyIfNotBlank("Gain: ", "" + classement.getGain()));
        Coureur coureur = selectionnerCoureur();
        Course course = selectionnerCourse();
        Classement classementMaj = classementController.updateClassement(new Classement(classement.getIdClassement(), place, gain, coureur, course));
        if (classementMaj == null) affMsg("Mise à jour infructueuse");
        else affMsg("Mise à jour effectuée : " + classementMaj);
    }
    @Override
    public Classement selectionner() {
        int nl = choixElt(lc);
        return lc.get(nl - 1);
    }

    private Coureur selectionnerCoureur() {
        System.out.print("Coureurs : ");
        List<Coureur> coureurs= coureurController.getAll();
        affList(coureurs);
        System.out.println("Entrez l'id du coureur :");
        int idCoureur = sc.nextInt();
        sc.nextLine();
        for (Coureur c : coureurs){
            if (c.getIdCoureur()==idCoureur)
                return c;
        }
        return null;
    }

    private Course selectionnerCourse() {
        System.out.print("Course : ");
        List<Course> courses= courseController.getAll();
        affList(courses);
        System.out.println("Entrez l'id de la course :");
        int idCourse = sc.nextInt();
        sc.nextLine();
        for (Course c : courses){
            if (c.getIdCourse()==idCourse)
                return c;
        }
        return null;
    }
    private int choixElt(List<Classement> liste) {
        for (int i = 0; i < liste.size(); i++) {
            System.out.println((i + 1) + ". " + liste.get(i));
        }
        System.out.print("Choix: ");
        int choix = Integer.parseInt(sc.nextLine());
        return choix;
    }
}
