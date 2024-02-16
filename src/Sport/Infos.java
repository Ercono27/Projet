package Sport;

import java.util.Date;
import java.util.Objects;

public class Infos {
    private int idInfos;
    private Date departDate;
    private Ville ville;
    public Infos(){
    }

    public int getIdInfos() {
        return idInfos;
    }

    public void setIdInfos(int idInfos) {
        this.idInfos = idInfos;
    }

    public Date getDepartDate() {
        return departDate;
    }
    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }
    public Ville getVille() {
        return ville;
    }
    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infos infos = (Infos) o;
        return idInfos == infos.idInfos;
    }

}
