package mvc.Model;

import Sport.Coureur;
import Sport.CoureurPlaceGain;
import Sport.Course;
import Sport.Ville;
import mvc.Observer.Subject;

import java.math.BigDecimal;
import java.util.List;

public abstract class DAOCourse extends Subject {
    public abstract Course addCourse(Course course);

    public abstract boolean removeCourse(Course course);

    public abstract Course updateCourse(Course course);

    public abstract Course readCourse(int idCourse);

    public abstract List<Course> getCourses();


    public abstract List<CoureurPlaceGain> listeCoureurPlaceGain(Course course);
    public abstract BigDecimal gainTotal(Course course);
    public abstract Coureur vainqueur(Course course);
    public abstract boolean adCoureur(Coureur coureur,Course course);
    public abstract boolean supCoureur(Coureur coureur,Course course);
    public abstract  boolean resultat(Coureur coureur,int place,BigDecimal gain,Course course);
    public abstract  boolean modifierC(Coureur coureur,int place,BigDecimal gain,Course course);
    public abstract  boolean adVille(Course course, Ville ville);
    public abstract  boolean supVille(Course course,Ville ville);
    public abstract  List<Ville> listeVille(Course course);
    public abstract  boolean classementComplet(Course course);

}
