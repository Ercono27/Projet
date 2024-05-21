package mvc.View;

import Sport.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class CourseViewConsole extends CourseAbstractView {
    private Scanner sc = new Scanner(System.in);
    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }

    public void menu(){
        update(courseController.getAll());
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



    private void modifier() {
        int nl = choixElt(lc);
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
        courseController.search(idCourse);
    }

    private void retirer() {
        int nl = choixElt(lc);
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
}

