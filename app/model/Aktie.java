package model;

import javax.persistence.*;
import java.util.Random;

/**
 *
 */

@Entity
//@Table(name = "Aktie", schema = "public")
public class Aktie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="aktien_seq")
    @SequenceGenerator(name="aktien_seq", sequenceName="aktien_seq", allocationSize=1)
    @Column(name = "id")
    private int id;
    public String name;
    public int kurs;

    // leerer Constructor muss ZWINGEND vorhanden sein!!
    // sonst: Caused by: org.hibernate.InstantiationException: No default constructor for entity:  : model.Aktie
    public Aktie() {
    }

    public Aktie(String name) {
        this.name = name;
    }

    public Aktie(int id, String name, int kurs) {
        this.id = id;
        this.name = name;
        this.kurs = kurs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKurs() {
        int minimum = 1;
        int maximum = 16000;
        Random rn = new Random();
        int n = maximum - minimum + 1;
        int i = rn.nextInt() % n;
        int randomNum =  minimum + i;
        setKurs(randomNum);
        return kurs;
    }

    public void setKurs(int kurs) {
        this.kurs = kurs;
    }

    public int getId() {
        return id;
    }
}
