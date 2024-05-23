package designpatterns.composite;

import java.math.BigDecimal;

public class TestComposite {
    public static void main(String[] args) {
        Course course1 = new Course(1, "Course 1", new BigDecimal("100000"), 23);
        Course course2 = new Course(2, "Course 2", new BigDecimal("150000"), 24);
        Course course3 = new Course(3, "Course 3", new BigDecimal("200000"), 25);

        Championnat sousChampionnat1 = new Championnat(101, "Sous-Championnat 1");
        sousChampionnat1.addElement(course1);
        sousChampionnat1.addElement(course2);

        Championnat sousChampionnat2 = new Championnat(102, "Sous-Championnat 2");
        sousChampionnat2.addElement(course3);


        Championnat championnatPrincipal = new Championnat(100, "Championnat Principal");
        championnatPrincipal.addElement(sousChampionnat1);
        championnatPrincipal.addElement(sousChampionnat2);

        System.out.println(championnatPrincipal);
        championnatPrincipal.cashprice();
    }
}