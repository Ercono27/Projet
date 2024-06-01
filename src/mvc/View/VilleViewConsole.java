package mvc.View;

import Sport.Coureur;
import Sport.Course;
import Sport.Ville;
import mvc.Controller.VilleController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class VilleViewConsole extends VilleAbstractView {
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
        update(villeController.getAll());
        do{
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "Avoir les coureurs provenant d'une ville","fin"));

            switch(ch){
                case 1: ajouter();
                    break;
                case 2 : retirer();
                    break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5: getCoureurByVille();
                    break;
                case 6 : return;
            }
        }while(true);
    }

    private void modifier() {
        int nl = choixListe(lv);
        Ville vi = lv.get(nl-1);
        String nom = modifyIfNotBlank("Nom de la ville",vi.getNom());
        double latitude = Double.parseDouble(modifyIfNotBlank("Latitude :",""+vi.getLatitude()));
        double longitude = Double.parseDouble(modifyIfNotBlank("Longitude :",""+vi.getLongitude()));
        String pays=modifyIfNotBlank("Pays :",vi.getPays());
        Ville prmaj=villeController.update(new Ville(vi.getIdVille(),nom,latitude,longitude,pays));
        if(prmaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+prmaj);
    }

    private void rechercher() {
        System.out.println("idVille : ");
        int idVille = sc.nextInt();
        Ville v=villeController.search(idVille);
        if(v==null) affMsg("recherche infructueuse");
        else {
            affMsg(v.toString());

        }
    }

    private void retirer() {

        int nl = choixListe(lv);
        Ville pr = lv.get(nl-1);
        boolean ok = villeController.removeVille(pr);
        if(ok) affMsg("Course effacé");
        else affMsg("Course non effacé");
    }

    private void ajouter() {
        System.out.print("nom de la Ville : ");
        String nom= sc.nextLine();
        System.out.print("Latitude :");
        double latitude = sc.nextInt();
        System.out.print("Longitude :");
        double longitude = sc.nextDouble();
        sc.nextLine();
        System.out.println("Pays :");
        String pays=sc.nextLine();
        Ville pr = villeController.addVille(new Ville(0,nom,latitude,longitude,pays)) ;
        if(pr!=null) affMsg("création de :"+pr);
        else affMsg("erreur de création");
    }
    private void getCoureurByVille(){
        int nl = choixListe(lv);
        Ville pr = lv.get(nl-1);
        List<Coureur> lc =villeController.getCoureurByVille(pr.getIdVille());
        affList(lc);
    }

    @Override
    public Ville selectionner(){
        update(villeController.getAll());
        int nl =  choixListe(lv);
        return lv.get(nl-1);
    }
}
