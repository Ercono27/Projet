package designpatterns.composite;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Championnat extends Element{
    private String nom;
    private Set<Element> elts=new HashSet<>();
    public Championnat(int id,String nom){
        super(id);
        this.nom=nom;
    }
    public void addElement(Element element) {
        elts.add(element);
    }
    @Override
    public String toString() {
        StringBuilder aff= new StringBuilder(getId()+" "+nom+"\n");
        for(Element e:elts){
            aff.append(e+"\n");
        }
        return aff+"Cash prize de " +nom +" = "+cashprice()+"\n";
    }
    @Override
    public BigDecimal cashprice() {
        BigDecimal somme= BigDecimal.ZERO;
        for(Element e:elts){
            somme = somme.add(e.cashprice());
        }
        return somme;
    }

    public Set<Element> getElts() {
        return elts;
    }
}
