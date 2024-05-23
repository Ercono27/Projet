/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpatterns.composite;

import java.math.BigDecimal;

/**
 *
 * @author Michel
 */
public abstract class Element {
    private int id;
    public Element(int id){
        this.id=id;
    }
    public Element(){}

    public int getId() {
        return id;
    }
    
      public abstract BigDecimal cashprice();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Element other = (Element) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
