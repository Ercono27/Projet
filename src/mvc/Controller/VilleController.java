package mvc.Controller;
import Sport.Coureur;
import Sport.Ville;
import mvc.Model.DAOVille;
import mvc.View.VilleAbstractView;

import java.util.List;

public class VilleController {
    private DAOVille model;
    private VilleAbstractView view;

    public VilleController(DAOVille model, VilleAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Ville>getAll(){return model.getVilles();}
    public Ville addVille(Ville ville) {return model.addVille(ville);}
    public boolean removeVille(Ville ville) {return model.removeVille(ville);}
    public Ville update(Ville ville) {return model.updateVille(ville);}
    public Ville search(int idVille) {return model.readVille(idVille);}
    public List<Coureur> getCoureurByVille(int idVille){return model.getCoureurByVille(idVille);}
}
