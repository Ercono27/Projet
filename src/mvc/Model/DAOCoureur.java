package mvc.Model;

import Sport.Coureur;
import mvc.Observer.Subject;

import java.math.BigDecimal;
import java.util.List;

public abstract class DAOCoureur extends Subject {
    public abstract Coureur addCoureur(Coureur coureur);

    public abstract boolean removeCoureur(Coureur coureur);

    public abstract Coureur updateCoureur(Coureur coureur);

    public abstract Coureur readCoureur(int idCoureur);

    public abstract List<Coureur> getCoureurs();
    public abstract BigDecimal montant(int idCoureur);
}
