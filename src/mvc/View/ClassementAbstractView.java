package mvc.View;

import Sport.Classement;
import Sport.Infos;
import mvc.Controller.ClassementController;
import mvc.Observer.Observer;

import java.util.List;

public abstract class ClassementAbstractView implements Observer {
    protected ClassementController controller;
    protected List<Infos> li;

    public void setController(ClassementController controller) {this.controller = controller;}
    public abstract void affMsg(String msg);
    public abstract Classement selectionner();
    public abstract void menu();
    public abstract void affList(List l);

    @Override
    public void update(List li) {
        this.li=li;
        affList(li);
    }
}
