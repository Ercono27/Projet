package Sport;

import java.math.BigDecimal;
import java.util.Objects;

public class Classement {
    private int idClassement;
    private int place;
    private BigDecimal gain;
    private Coureur cour;
    public Classement(){
    }

    public int getIdClassement() {
        return idClassement;
    }

    public void setIdClassement(int idClassement) {
        this.idClassement = idClassement;
    }

    public int getPlace() {
        return place;
    }
    public void setPlace(int place) {
        this.place = place;
    }
    public BigDecimal getGain() {
        return gain;
    }
    public void setGain(BigDecimal gain) {
        this.gain = gain;
    }
    public Coureur getCour() {
        return cour;
    }
    public void setCour(Coureur cour) {
        this.cour = cour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classement that = (Classement) o;
        return idClassement == that.idClassement;
    }

}
