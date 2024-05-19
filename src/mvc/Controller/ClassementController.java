package mvc.Controller;

import Sport.Classement;
import mvc.Model.DAOClassement;
import mvc.View.ClassementAbstractView;

import java.util.List;

public class ClassementController {
    private DAOClassement model;
    private ClassementAbstractView view;

    public ClassementController(DAOClassement model, ClassementAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Classement> getAll() {return model.getClassements();}
    public Classement addClassement(Classement classement) {return model.addClassement(classement);}
    public boolean removeClassement(Classement classement) {return model.removeClassement(classement);}
    public Classement updateClassement(Classement classement) {return model.updateClassement(classement);}
    public Classement search(int idClassement) {return model.readClassement(idClassement);}
    public Classement getClassementById(int id) {return model.readClassement(id);}

}
