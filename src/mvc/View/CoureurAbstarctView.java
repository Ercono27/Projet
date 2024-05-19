package mvc.View;

import Sport.*;
import mvc.Controller.CoureurController;
import mvc.Observer.Observer;

import java.util.List;

public class CoureurAbstarctView implements Observer {
    protected CoureurController coureurController;
    protected List<Coureur> lc;

    public void setController(CoureurController coureurController){this.coureurController=coureurController;}
    public abstract void affMsg(String msg);
    public abstract Coureur selectionner();
    public abstract void menu();
    public abstract void affList(List l);

    @Override
    public void update(List lc){
        this.lc=lc;
        affList(lc);
    }
}
