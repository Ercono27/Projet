package mvc.Controller;

import Sport.Infos;
import mvc.Model.DAOInfos;
import mvc.View.InfosAbstractView;

import java.util.List;

public class InfosController {
    private DAOInfos model;
    private InfosAbstractView view;

    public InfosController(DAOInfos model, InfosAbstractView view){
        this.model=model;
        this.view=view;
        this.view.setController(this);
    }
    public List<Infos> getAll(){return model.getInfos();}
    public Infos addInfos(Infos infos){return model.addInfos(infos);}
    public boolean removeInfos(Infos infos){return model.removeInfos(infos);}

    public Infos update(Infos infos) {return model.updateInfos(infos);}
    public Infos search(int idInfos) {return model.readInfos(idInfos);}
}
