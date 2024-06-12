package mvc.Controller;

import Sport.Coureur;
import mvc.Model.DAOCoureur;
import mvc.View.CoureurAbstarctView;

import java.math.BigDecimal;
import java.util.List;

public class CoureurController {
    private DAOCoureur model;
    private CoureurAbstarctView view;
    public CoureurController(DAOCoureur model, CoureurAbstarctView view){
        this.model=model;
        this.view=view;
        this.view.setController(this);
    }

    public List<Coureur> getAll(){return model.getCoureurs();}
    public Coureur addCoureur(Coureur coureur){return model.addCoureur(coureur);}
    public boolean removeCoureur(Coureur coureur){return model.removeCoureur(coureur);}
    public Coureur update(Coureur coureur){return model.updateCoureur(coureur);}
    public Coureur search(int idCoureur){return model.readCoureur(idCoureur);}
    public BigDecimal montant(int idCoureur){return model.montant(idCoureur);}
}
