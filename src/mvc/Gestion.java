package mvc;

import mvc.Controller.*;
import mvc.Model.*;
import mvc.View.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class Gestion {
    private DAOCourse cm;
    private CourseController cc;
    private CourseAbstractView cv;
    private DAOClassement clm;
    private ClassementController clc;
    private ClassementAbstractView clv;
    private DAOCoureur com;
    private CoureurController coc;
    private CoureurAbstarctView cov;
    private DAOInfos im;
    private InfosController ic;
    private InfosAbstractView iv;
    private DAOVille vm;
    private VilleController vc;
    private VilleAbstractView vv;

    public void gestion(){
        cm = new CourseModelDB();
        clm= new ClassementModelDB();
        com=new CoureurModelDB();
        im=new InfosModelDB();
        vm=new VilleModelDB();

        cv = new CourseViewConsole();
        clv=new ClassementViewConsole();
        cov=new CoureurViewConsole();
        iv=new InfosViewConsole();
        vv=new VilleViewConsole();

        cc = new CourseController(cm,cv);
        clc = new ClassementController(clm,clv);
        coc=new CoureurController(com,cov);
        ic=new InfosController(im,iv);
        vc=new VilleController(vm,vv);

        cm.addObserver(cv);
        clm.addObserver(clv);
        com.addObserver(cov);
        im.addObserver(iv);
        vm.addObserver(vv);

        List<String> loptions = Arrays.asList("Course","Classement","Coureur","Infos","Ville","Quitter");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: cv.menu();
                    break;
                case 2 : clv.menu();
                    break;
                case 3: cov.menu();
                    break;
                case 4: iv.menu();
                    break;
                case 5: vv.menu();
                    break;
                case 6: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
        Gestion gm = new Gestion();
        gm.gestion();
    }

}

