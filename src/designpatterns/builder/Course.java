package designpatterns.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {

    protected int idCourse;

    protected String nom;
    protected BigDecimal priceMoney;
    protected int km;
    protected List<Infos> listeInfo = new ArrayList<>();
    protected List<Classement> listeClassement = new ArrayList<>();


    private Course(CourseBuilder cb) {
        this.idCourse = cb.idCourse;
        this.nom = cb.nom;
        this.priceMoney = cb.priceMoney;
        this.km = cb.km;
    }
    public int getIdCourse() {
        return idCourse;
    }
    public String getNom() {
        return nom;
    }
    public BigDecimal getPriceMoney() {
        return priceMoney;
    }
    public int getKm() {
        return km;
    }
    public List<Infos> getListeInfo() {
        return listeInfo;
    }
    public List<Classement> getListeClassement() {
        return listeClassement;
    }
    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", nom='" + nom + '\'' +
                ", priceMoney=" + priceMoney +
                ", km=" + km +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return idCourse == course.idCourse && km == course.km && Objects.equals(nom, course.nom) && Objects.equals(priceMoney, course.priceMoney) && Objects.equals(listeInfo, course.listeInfo) && Objects.equals(listeClassement, course.listeClassement);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idCourse, nom, priceMoney, km, listeInfo, listeClassement);
    }

    public static class CourseBuilder {
        protected int idCourse;
        protected String nom;
        protected BigDecimal priceMoney;
        protected int km;
        public CourseBuilder setIdCourse(int idCourse) {
            this.idCourse = idCourse;
            return this;
        }

        public CourseBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public CourseBuilder setPriceMoney(BigDecimal priceMoney) {
            this.priceMoney = priceMoney;
            return this;
        }

        public CourseBuilder setKm(int km) {
            this.km = km;
            return this;
        }
        public Course build() throws Exception {
            if (idCourse <= 0 || nom == null || nom.isEmpty() || priceMoney == null || km <= 0) {
                throw new Exception("Informations de construction incomplètes");
            }
            return new Course(this);
        }
    }

}

