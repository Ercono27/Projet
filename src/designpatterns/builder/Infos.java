package designpatterns.builder;

import Sport.*;

import java.time.LocalDate;
import java.util.Objects;

public class Infos {
    protected int idInfos;
    protected LocalDate departDate;
    protected Ville ville;
    private Course course;
    public Infos(InfosBuilder builder) {
        this.idInfos = builder.idInfos;
        this.departDate = builder.departDate;
        this.ville = builder.ville;
        this.course=builder.course;
    }
    public int getIdInfos() {
        return idInfos;
    }
    public LocalDate getDepartDate() {
        return departDate;
    }
    public Ville getVille() {
        return ville;
    }
    public Course getCourse(){return course;};
    @Override
    public String toString() {
        return "Infos{" +
                "idInfos=" + idInfos +
                ", departDate=" + departDate +
                ", ville=" + ville +
                ", course=" + course +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infos infos = (Infos) o;
        return idInfos == infos.idInfos && Objects.equals(departDate, infos.departDate) && Objects.equals(ville, infos.ville) && Objects.equals(course, infos.course);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idInfos, departDate, ville, course);
    }
    public static class InfosBuilder {
        protected int idInfos;
        protected LocalDate departDate;
        protected Ville ville;
        protected Course course;
        public InfosBuilder setIdInfos(int idInfos) {
            this.idInfos = idInfos;
            return this;
        }
        public InfosBuilder setDepartDate(LocalDate departDate) {
            this.departDate = departDate;
            return this;
        }
        public InfosBuilder setVille(Ville ville) {
            this.ville = ville;
            return this;
        }
        public InfosBuilder setCourse(Course course){
            this.course=course;
            return this;
        }
        public Infos build() throws Exception {
            if (idInfos <= 0 || departDate == null || ville == null||course==null) {
                throw new Exception("Informations de construction incomplètes");
            }
            return new Infos(this);
        }
    }
}
