package mvc;

import mvc.Controller.CourseController;
import mvc.Model.DAOCourse;
import mvc.Model.ModelCourseDB;
import mvc.View.CourseAbstractView;
import mvc.View.CourseViewConsole;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class Gestion {
    private DAOCourse cm;
    private CourseController cc;
    private CourseAbstractView cv;

    public void gestion(){
        cm = new ModelCourseDB();

        cv = new CourseViewConsole();

        cc = new CourseController(cm,cv);//création et injection de dépendance

        cm.addObserver(cv);

        List<String> loptions = Arrays.asList("clients","commandes","produits","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: cv.menu();
                    break;
                case 2 : cfv.menu();
                    break;
                case 3: pv.menu();
                    break;
                case 4: System.exit(0);
            }
        }while(true);
    }

}
