package mvc.View;

import Sport.*;
import mvc.Controller.CoureurController;
import mvc.Controller.CourseController;
import mvc.Controller.VilleController;
import mvc.Model.CoureurModelDB;
import mvc.Model.VilleModelDB;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class CourseViewConsole extends CourseAbstractView {
    private Scanner sc = new Scanner(System.in);
    private CoureurController coureurController;
    private VilleController villeController;
    @Override
    public void affMsg(String msg) {
        System.out.println(msg);
    }
    @Override
    public void affList(List infos) {
        affListe(infos);
    }
    public CourseViewConsole(){
        this.coureurController=new CoureurController(new CoureurModelDB(),new CoureurViewConsole());
        this.villeController=new VilleController(new VilleModelDB(),new VilleViewConsole());
    }
    public void menu(){
        update(courseController.getAll());
        do{
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "Méthodes spéciales","fin"));
            switch(ch){
                case 1: ajouter();
                    break;
                case 2 : retirer();
                    break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5: Course c=selectionnerCourse();
                    special(c);
                case 6 : return;
            }
        }while(true);
    }
    private void special(Course c) {
        affMsg(c.toString());
        do {
            int ch = choixListe(Arrays.asList("liste des coureurs avec leurs places et leurs gains", "Gain total", "Vainqueur","Ajouter un coureur.","Supprimer coureur", "Résultat", "Modifier","Ajouter une ville","Supprimer une ville","Liste des villes","Le Classement de cette course est-il complet?","Fin"));
            switch (ch) {
                case 1:
                    listeCoureurPlaceGain(c);
                    break;
                case 2:
                    gainTotal(c);
                    break;
                case 3:
                    vainqueur(c);
                    break;
                case 4:
                    adCoureur(c);
                    break;
                case 5:
                    supCoureur(c);
                    break;
                case 6:
                    resultat(c);
                    break;
                case 7:
                    modifierC(c);
                    break;
                case 8:
                    adVille(c);
                    break;
                case 9:
                    supVille(c);
                    break;
                case 10:
                    listeVille(c);
                    break;
                case 11:
                    classementComplet(c);
                    break;
                case 12:
                    return;
            }
        } while (true);
    }
    public void listeCoureurPlaceGain(Course c){
        List<CoureurPlaceGain> coureurs= courseController.listeCoureurPlaceGain(c);
        if (coureurs.isEmpty()) affMsg("Aucun coureur trouvé.");
        else affList(coureurs);
    }
    public void gainTotal(Course c){
        BigDecimal total=courseController.gainTotal(c);
        affMsg("Gain total de la course "+c.getNom()+" : "+total+" €.");
    }
    public void vainqueur(Course c){
        Coureur coureur=courseController.vainqueur(c);
        if (coureur==null) affMsg("Aucun vainqueur pour la course.");
        else affMsg("Le vainqueur de la course est : "+coureur);
    }
    public void adCoureur(Course c){
        Coureur coureur= selectionnerCoureur();
        boolean ok = courseController.adCoureur(coureur,c);
        if(ok) affMsg("Coureur ajouté.");
        else affMsg("Erreur lors de l'ajout du coureur.");
    }
    public void supCoureur(Course c){
        Coureur coureur= selectionnerCoureur2(c);
        boolean ok = courseController.supCoureur(coureur,c);
        if(ok) affMsg("Coureur supprimé.");
        else affMsg("Erreur lors de la suppression du coureur.");
    }
    public void resultat(Course c){
        Coureur coureur= selectionnerCoureur2(c);
        System.out.println("Quelle place ?");
        int place=sc.nextInt();
        System.out.println("Quelle gain ?");
        BigDecimal gain=sc.nextBigDecimal();
        boolean ok= courseController.resultat(coureur,place,gain,c);
        if(ok) affMsg("Résultat bien enregistré.");
        else affMsg("Erreur lors de l'enregistrement du résultat.");
    }
    public void modifierC(Course c){
        Coureur coureur= selectionnerCoureur2(c);
        System.out.println("Quelle place ?");
        int place=sc.nextInt();
        System.out.println("Quelle gain ?");
        BigDecimal gain=sc.nextBigDecimal();
        boolean ok= courseController.modifierC(coureur,place,gain,c);
        if(ok) affMsg("Résultat bien modifié.");
        else affMsg("Erreur lors de la modification du résultat.");
    }
    public void adVille(Course c){
        Ville ville=selectionnerVille();
        boolean ok = courseController.adVille(c,ville);
        if(ok) affMsg("Ville bien ajoutée.");
        else affMsg("Erreur lors de l'ajout de la ville.");
    }
    public void supVille(Course c){
        Ville ville=selectionnerVille2(c);
        boolean ok = courseController.supVille(c,ville);
        if(ok) affMsg("Ville bien supprimée.");
        else affMsg("Erreur lors de la suppression de la ville.");
    }
    public void listeVille(Course c){
        List<Ville> villes=courseController.listeVille(c);
        if(villes.isEmpty())affMsg("Aucune ville trouvée pour cette course.");
        else affList(villes);
    }
    public void classementComplet(Course c){
        boolean ok=courseController.classementComplet(c);
        if (ok)affMsg("Le classement est bien complet.");
        else affMsg("Le classement est incomplet.");
    }

    private void modifier() {
        int nl = choixListe(lc);
        Course co = lc.get(nl-1);
        String nom = modifyIfNotBlank("nom de la Course",co.getNom());
        BigDecimal pm = new BigDecimal(modifyIfNotBlank("Price money",""+co.getPriceMoney()));
        int km = Integer.parseInt(modifyIfNotBlank("Kilometrage : ",""+co.getKm()));
        Course prmaj =  courseController.update(new Course(co.getIdCourse(),nom,pm,km));
        if(prmaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+prmaj);
    }

    private void rechercher() {
        System.out.println("idCourse : ");
        int idCourse = sc.nextInt();
        Course c=courseController.search(idCourse);
        if(c==null) affMsg("recherche infructueuse");
        else {
            affMsg(c.toString());

        }
    }

    private void retirer() {
        int nl = choixListe(lc);
        Course pr = lc.get(nl-1);
        boolean ok = courseController.removeCourse(pr);
        if(ok) affMsg("Course effacé");
        else affMsg("Course non effacé");
    }

    private void ajouter() {
        System.out.print("nom de la Course : ");
        String nomcour= sc.nextLine();
        System.out.print("Price money : ");
        BigDecimal pm = sc.nextBigDecimal();
        System.out.print("Kilometrage :");
        int km = sc.nextInt();
        Course pr = courseController.addCourse(new Course(0,nomcour,pm,km)) ;
        if(pr!=null) affMsg("création de :"+pr);
        else affMsg("erreur de création");
    }

    @Override
    public Course selectionner(){
        update(courseController.getAll());
        int nl =  choixListe(lc);
        Course pr = lc.get(nl-1);
        return pr;
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
    private Coureur selectionnerCoureur2(Course course) {
        System.out.print("Coureurs : ");
        List<CoureurPlaceGain> coureurs= courseController.listeCoureurPlaceGain(course);
        affList(coureurs);
        System.out.println("Entrez l'id du coureur :");
        int idCoureur = sc.nextInt();
        sc.nextLine();
        for (CoureurPlaceGain c : coureurs){
            if (c.getCoureur().getIdCoureur()==idCoureur)
                return c.getCoureur();
        }
        return null;
    }
    private Ville selectionnerVille() {
        System.out.println("Villes disponibles :");
        List<Ville> villes = villeController.getAll();
        affList(villes);
        System.out.print("Entrez l'ID de la ville de résidence : ");
        int idVille = sc.nextInt();
        sc.nextLine();
        for (Ville ville : villes) {
            if (ville.getIdVille() == idVille) {
                return ville;
            }
        }
        return null;
    }

    private Ville selectionnerVille2(Course c) {
        System.out.println("Villes disponibles :");
        List<Ville> villes = courseController.listeVille(c);
        affList(villes);
        System.out.print("Entrez l'ID de la ville de résidence : ");
        int idVille = sc.nextInt();
        sc.nextLine();
        for (Ville ville : villes) {
            if (ville.getIdVille() == idVille) {
                return ville;
            }
        }
        return null;
    }


    private Course selectionnerCourse(){
        System.out.println("Course disponibles :");
        List<Course> courses=courseController.getAll();
        affList(courses);
        System.out.println("Entrez l'id de la course : ");
        int idCourse=sc.nextInt();
        sc.nextLine();
        for (Course course:courses){
            if (course.getIdCourse()==idCourse)
                return course;
        }
        return null;
    }
}

