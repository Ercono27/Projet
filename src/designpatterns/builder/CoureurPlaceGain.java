package designpatterns.builder;

import java.math.BigDecimal;

public class CoureurPlaceGain {
    protected Coureur coureur;
    protected int place;
    protected BigDecimal gain;

    private CoureurPlaceGain(CoureurPlaceGainBuilder builder) {
        this.coureur = builder.coureur;
        this.place = builder.place;
        this.gain = builder.gain;
    }
    public Coureur getCoureur() {
        return coureur;
    }
    public int getPlace() {
        return place;
    }
    public BigDecimal getGain() {
        return gain;
    }
    public static class CoureurPlaceGainBuilder {
        protected Coureur coureur;
        protected int place;
        protected BigDecimal gain;

        public CoureurPlaceGainBuilder setCoureur(Coureur coureur) {
            this.coureur = coureur;
            return this;
        }
        public CoureurPlaceGainBuilder setPlace(int place) {
            this.place = place;
            return this;
        }
        public CoureurPlaceGainBuilder setGain(BigDecimal gain) {
            this.gain = gain;
            return this;
        }
        public CoureurPlaceGain build() throws Exception {
            if (coureur == null || place <= 0 || gain == null) {
                throw new Exception("Informations de construction incomplètes");
            }
            return new CoureurPlaceGain(this);
        }
    }
}
