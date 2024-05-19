package mvc.Model;

import Sport.Classement;
import mvc.Observer.Subject;

import java.util.List;

public abstract class DAOClassement extends Subject {
    public abstract Classement addClassement(Classement classement);
    public abstract boolean removeClassement(Classement classement);
    public abstract Classement updateClassement(Classement classement);
    public abstract Classement readClassement(int idClassement);
    public abstract List<Classement> getClassements();
}
