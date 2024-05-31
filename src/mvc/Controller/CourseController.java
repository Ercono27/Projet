package mvc.Controller;
import Sport.Coureur;
import Sport.CoureurPlaceGain;
import Sport.Course;
import Sport.Ville;
import mvc.Model.DAOCourse;
import mvc.View.CourseAbstractView;

import java.math.BigDecimal;
import java.util.List;

public class CourseController {
    private DAOCourse model;
    private CourseAbstractView view;

    public CourseController(DAOCourse model,CourseAbstractView view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Course> getAll(){return model.getCourses();}
    public Course addCourse(Course course){return model.addCourse(course);}
    public boolean removeCourse(Course course){return model.removeCourse(course);}
    public Course update(Course course){return model.updateCourse(course);}
    public Course search(int idCourse){return model.readCourse(idCourse);}
    public List<CoureurPlaceGain> listeCoureurPlaceGain(Course course){return  model.listeCoureurPlaceGain(course);}
    public BigDecimal gainTotal(Course course){return model.gainTotal(course);}
    public Coureur vainqueur(Course course){return model.vainqueur(course);}
    public boolean adCoureur(Coureur coureur,Course course){return model.adCoureur(coureur,course);}
    public boolean supCoureur(Coureur coureur,Course course){return model.supCoureur(coureur,course);}
    public boolean resultat(Coureur coureur,int place,BigDecimal gain,Course course){return model.resultat(coureur,place,gain,course);}
    public boolean modifierC(Coureur coureur,int place,BigDecimal gain,Course course){return model.modifierC(coureur,place,gain,course);}
    public boolean adVille(Course course, Ville ville){return model.adVille(course,ville);}
    public boolean supVille(Course course,Ville ville){return model.supVille(course,ville);}
    public List<Ville> listeVille(Course course){return model.listeVille(course);}
    public boolean classementComplet(Course course){return model.classementComplet(course);}
}
