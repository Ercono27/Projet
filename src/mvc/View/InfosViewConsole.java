package mvc.View;

import Sport.Course;
import Sport.Infos;
import Sport.Ville;
import mvc.Controller.CourseController;
import mvc.Controller.VilleController;
import mvc.Model.CourseModelDB;
import mvc.Model.VilleModelDB;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class InfosViewConsole extends InfosAbstractView {
    private Scanner sc = new Scanner(System.in);

    private VilleController villeController;
    private CourseController courseController;
    public InfosViewConsole(){
        this.villeController = new VilleController(new VilleModelDB(), new VilleViewConsole());
        this.courseController = new CourseController(new CourseModelDB(), new CourseViewConsole());
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Information: " + msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }

    public void menu() {
        update(infosController.getAll());
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.print("Date de départ: ");
        LocalDate departDate = LocalDate.parse(sc.nextLine(), formatter);
        Ville ville = selectionnerVille();
        Course course=selectionnerCourse();
        Infos infos = new Infos(0, departDate, ville, course);
        Infos nouvelleInfos = infosController.addInfos(infos);
        if (nouvelleInfos != null) affMsg("Création de : " + nouvelleInfos);
        else affMsg("Erreur de création");
    }

    private void modifier() {
        int nl = choixElt(li);
        Infos infos = li.get(nl - 1);
        LocalDate departDate = LocalDate.parse(modifyIfNotBlank("Date de départ: ", "" + infos.getDepartDate()));
        Ville ville = selectionnerVille();
        Course course=selectionnerCourse();
        Infos infosMaj = infosController.update(new Infos(infos.getIdInfos(), departDate, ville,course));
        if (infosMaj == null) affMsg("Mise à jour infructueuse");
        else affMsg("Mise à jour effectuée : " + infosMaj);
    }

    private void rechercher() {
        System.out.print("ID Infos : ");
        int idInfos = sc.nextInt();
        Infos infos = infosController.search(idInfos);
        if (infos != null) affMsg("Infos trouvées : " + infos);
        else affMsg("Aucune infos trouvée avec cet ID");
    }

    private void retirer() {
        int nl = choixElt(li);
        Infos infos = li.get(nl - 1);
        boolean ok = infosController.removeInfos(infos);
        if (ok) affMsg("Infos supprimées");
        else affMsg("Infos non supprimées");
    }

    @Override
    public Infos selectionner() {
        int nl = choixElt(li);
        return li.get(nl - 1);
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
    private Course selectionnerCourse() {
        System.out.println("Courses disponibles :");
        List<Course> courses = courseController.getAll();
        affList(courses);
        System.out.print("Entrez l'ID de la course : ");
        int idCourse = sc.nextInt();
        sc.nextLine();
        for (Course course : courses) {
            if (course.getIdCourse() == idCourse) {
                return course;
            }
        }
        return null;
    }
}
