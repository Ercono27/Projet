package mvc.Model;

import Sport.Infos;
import mvc.Observer.Subject;

import java.util.List;

public abstract class DAOInfos extends Subject {
    public abstract Infos addInfos(Infos infos);
    public abstract boolean removeInfos(Infos infos);
    public abstract Infos updateInfos(Infos infos);
    public abstract Infos readInfos(int idInfos);
    public abstract List<Infos> getInfos();
    public abstract List getNotification();
}
