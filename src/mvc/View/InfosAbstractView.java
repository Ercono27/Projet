package mvc.View;

import Sport.Infos;
import mvc.Controller.InfosController;
import mvc.Observer.Observer;

import java.util.List;

public abstract class InfosAbstractView implements Observer {
    protected InfosController infosController;
    protected List<Infos> li;


    public void setController(InfosController infosController){this.infosController=infosController;}
    public abstract void affMsg(String msg);
    public abstract Infos selectionner();
    public abstract void menu();
    public abstract void affList(List l);

    @Override
    public void update(List li){
        this.li=li;
        affList(li);
    }
}
