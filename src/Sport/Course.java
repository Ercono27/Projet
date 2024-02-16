package Sport;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Course {
    private int idCourse;
    private String nom;
    private BigDecimal priceMoney;
    private int km;
    private List<Infos> listeInfo = new ArrayList<>();
    private List<Classement> listeClassement = new ArrayList<>();

    public Course() {
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPriceMoney() {
        return priceMoney;
    }

    public void setPriceMoney(BigDecimal priceMoney) {
        this.priceMoney = priceMoney;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return idCourse == course.idCourse;
    }

    public void listeCorueursPlaceGain(){
        //une fois course finie, affiche le coureur avec ça place et ses gains
    }
    public BigDecimal gainTotal(){

    }
    public Coureur vainqueur(){
        for(Classement c : listeClassement){
            if (c.getPlace()==1)
                return c.getCour();
        }
    }
    public void addCoureur(Coureur c){
        Classement cl=new Classement();
        cl.setCour(c);
        listeClassement.add(cl);
    }
    public void supCoureur(Coureur c){
        for(Classement cl : listeClassement){
            if(cl.getCour().equals(c)){
                c.setIdCoureur(-1);
                c.setMatricule("");
                c.setNom("");
                c.setPrenom("");
                Date d=new Date();
                c.setDateNaiss(d);
                c.setNationalite("");
            }
        }
    }
    public void resultat(Coureur c,int place, BigDecimal gain) {
        for (Classement cl : listeClassement){
            if(cl.getCour().equals(c)){
                cl.setPlace(place);
                cl.setGain(gain);
            }
        }
    }
    public void modifier(Coureur c,int place, BigDecimal gain) {
        /*for (Classement cl : listeClassement){
            if(cl.getCour().equals(c)){
                cl.setPlace(place);
                cl.setGain(gain);
            }
        }*/
    }
    public void addVille(Ville ville){
        Infos i = new Infos();
        Date d=new Date();
        i.setVille(ville);
        i.setDepartDate(d);
        listeInfo.add(i);
    }
    public void supVille(Ville v){
        for(Infos i : listeInfo){
            if(i.getVille().equals(v)){
                v.setIdVille(-1);
                v.setNom("");
                v.setLatitude(-1);
                v.setLongitude(-1);
                v.setPays("");
            }
        }
    }
}
