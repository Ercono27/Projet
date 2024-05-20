package designpatterns.builder;

import java.math.BigDecimal;
import java.util.Objects;

public class Classement {
    protected int idClassement;
    protected int place;
    protected BigDecimal gain;
    protected Coureur cour;
    protected Course course;

    public Classement(ClassementBuilder builder) {
        this.idClassement = builder.idClassement;
        this.place = builder.place;
        this.gain = builder.gain;
        this.cour = builder.cour;
        this.course = builder.course;
    }

    public int getIdClassement() {
        return idClassement;
    }

    public int getPlace() {
        return place;
    }

    public BigDecimal getGain() {
        return gain;
    }

    public Coureur getCour() {
        return cour;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classement that = (Classement) o;
        return idClassement == that.idClassement && place == that.place && Objects.equals(gain, that.gain) && Objects.equals(cour, that.cour) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClassement, place, gain, cour, course);
    }

    @Override
    public String toString() {
        return "Classement{" +
                "idClassement=" + idClassement +
                ", place=" + place +
                ", gain=" + gain +
                ", cour=" + cour +
                ", course=" + course +
                '}';
    }

    public static class ClassementBuilder {
        protected int idClassement;
        protected int place;
        protected BigDecimal gain;
        protected Coureur cour;
        protected Course course;

        public ClassementBuilder setIdClassement(int idClassement) {
            this.idClassement = idClassement;
            return this;
        }

        public ClassementBuilder setPlace(int place) {
            this.place = place;
            return this;
        }

        public ClassementBuilder setGain(BigDecimal gain) {
            this.gain = gain;
            return this;
        }

        public ClassementBuilder setCour(Coureur cour) {
            this.cour = cour;
            return this;
        }

        public ClassementBuilder setCourse(Course course) {
            this.course = course;
            return this;
        }

        public Classement build() throws Exception {
            if (idClassement <= 0 || cour == null || course == null) {
                throw new Exception("Informations de construction incomplètes");
            }
            return new Classement(this);
        }
    }

}
