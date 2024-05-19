package mvc.View;
import Sport.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;
import static utilitaires.Utilitaire.choixListe;

public class CoureurViewConsole extends CoureurAbstarctView{
    private Scanner sc=new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }


    public void menu(){
        update(coureurController.getAll());
        do{
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));

            switch(ch){
                case 1: ajouter();
                    break;
                case 2 : retirer();
                    break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : return;
            }
        }while(true);
    }


    private void ajouter() {
        System.out.print("Matricule: ");
        String matricule = sc.nextLine();
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Prenom: ");
        String prenom = sc.nextLine();
        System.out.print("Date de naissance (AAAA-MM-JJ): ");
        LocalDate dateNaiss = LocalDate.parse(sc.nextLine());
        System.out.print("Nationalite: ");
        String nationalite = sc.nextLine();
        Ville villeResidence = ;
        Coureur coureur = new Coureur(0, matricule, nom, prenom, dateNaiss, nationalite, villeResidence);
        Coureur nouveauCoureur = coureurController.addCoureur(coureur);
        if(nouveauCoureur!=null) affMsg("création de :"+nouveauCoureur);
        else affMsg("erreur de création");
    }

    private void modifier() {
        int nl = choixElt(lc);
        Coureur co = lc.get(nl-1);
        String nom = modifyIfNotBlank("nom du Coureur",co.getNom());
        BigDecimal pm = new BigDecimal(modifyIfNotBlank("Price money",""+co.getPriceMoney()));
        int km = Integer.parseInt(modifyIfNotBlank("Kilometrage : ",""+co.getKm()));
        Course prmaj =  courseController.update(new Course(co.getIdCourse(),nom,pm,km));
        if(prmaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+prmaj);
    }

    private void rechercher() {
        System.out.println("idCourse : ");
        int idCourse = sc.nextInt();
        courseController.search(idCourse);
    }

    private void retirer() {

        int nl = choixElt(lc);
        Course pr = lc.get(nl-1);
        boolean ok = courseController.removeCourse(pr);
        if(ok) affMsg("Course effacé");
        else affMsg("Course non effacé");
    }

    @Override
    public Course selectionner(){
        update(courseController.getAll());
        int nl =  choixListe(lc);
        Course pr = lc.get(nl-1);
        return pr;
    }
}
