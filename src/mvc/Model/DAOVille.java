package mvc.Model;

import Sport.Coureur;
import Sport.Ville;
import mvc.Observer.Subject;

import java.util.List;

public abstract class DAOVille extends Subject {
    public abstract Ville addVille(Ville ville);

    public abstract boolean removeVille(Ville ville);

    public abstract Ville updateVille(Ville ville);

    public abstract Ville readVille(int idVille);

    public abstract List<Ville> getVilles();
    public abstract List<Coureur> getCoureurByVille(int idVille);
}
