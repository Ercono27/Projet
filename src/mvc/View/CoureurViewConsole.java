package mvc.View;
import Sport.*;
import mvc.Controller.VilleController;
import mvc.Model.VilleModelDB;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class CoureurViewConsole extends CoureurAbstarctView{
    private Scanner sc=new Scanner(System.in);
    private VilleController villeController;
    public CoureurViewConsole() {
        this.villeController = new VilleController(new VilleModelDB(), new VilleViewConsole());
    }

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.print("Matricule: ");
        String matricule = sc.nextLine();
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Prenom: ");
        String prenom = sc.nextLine();
        System.out.print("Date de naissance (dd-MM-yyyy): ");
        LocalDate dateNaiss = LocalDate.parse(sc.nextLine(), formatter);
        System.out.print("Nationalite: ");
        String nationalite = sc.nextLine();
        Ville villeResidence = selectionnerVille();
        Coureur coureur = new Coureur(0, matricule, nom, prenom, dateNaiss, nationalite, villeResidence);
        Coureur nouveauCoureur = coureurController.addCoureur(coureur);
        if(nouveauCoureur!=null) affMsg("création de :"+nouveauCoureur);
        else affMsg("erreur de création");
    }

    private void modifier() {
        int nl = choixListe(lc);
        Coureur co = lc.get(nl-1);
        String nom = modifyIfNotBlank("Nom du Coureur",co.getNom());
        String matricule = modifyIfNotBlank("Matricule",co.getMatricule());
        String prenom = modifyIfNotBlank("Prenom ",co.getNom());
        LocalDate dateNaiss = LocalDate.parse(modifyIfNotBlank("Date de naissance ",""+co.getDateNaiss()));
        String nationnalite = modifyIfNotBlank("Nationnalite ",co.getNationalite());
        Ville villeResidence = selectionnerVille();
        Coureur prmaj =  coureurController.update(new Coureur(co.getIdCoureur(),matricule,nom,prenom,dateNaiss,nationnalite,villeResidence));
        if(prmaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+prmaj);
    }

    private void rechercher() {
        System.out.println("idCoureur : ");
        int idCourse = sc.nextInt();
        Coureur c=coureurController.search(idCourse);
        if (c==null) affMsg("Recherche infructueuse.");
        else affMsg(c.toString());
    }

    private void retirer() {
        int nl = choixListe(lc);
        Coureur pr = lc.get(nl-1);
        boolean ok = coureurController.removeCoureur(pr);
        if(ok) affMsg("Course effacé");
        else affMsg("Course non effacé");
    }

    @Override
    public Coureur selectionner(){
        update(coureurController.getAll());
        int nl =  choixListe(lc);
        Coureur pr = lc.get(nl-1);
        return pr;
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
}
