package mvc.View;

import Sport.Classement;
import Sport.Infos;
import mvc.Controller.ClassementController;
import mvc.Observer.Observer;

import java.util.List;

public abstract class ClassementAbstractView implements Observer {
    protected ClassementController classementController;
    protected List<Classement> lc;

    public void setController(ClassementController controller) {this.classementController = controller;}
    public abstract void affMsg(String msg);
    public abstract Classement selectionner();
    public abstract void menu();
    public abstract void affList(List l);

    @Override
    public void update(List lc) {
        this.lc=lc;
        affList(lc);
    }
}
