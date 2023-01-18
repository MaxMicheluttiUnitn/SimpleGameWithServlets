package it.unitn.massimomichelutti.webarch.asn2.beans;

import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable {
    private Country country1,country2,country3;
    public Game(){
        country1=new Country();
        country2=new Country();
        country2=new Country();
    }
    public Game(Country c1, Country c2, Country c3){
        country1=c1;
        country2=c2;
        country3=c3;
    }

    /*
     * gets the first country
     */
    public Country getFirst(){return country1;}
    /*
     * gets the second country
     */
    public Country getSecond(){return country2;}
    /*
     * gets the third country
     */
    public Country getThird(){return country3;}

    @Override
    public String toString(){
        return country1.toString()+"<input type=\"number\" name=\"answer1\" required min=1 max=300><br>"+
                country2.toString()+"<input type=\"number\" name=\"answer2\" required min=1 max=300><br>"+
                country3.toString()+"<input type=\"number\" name=\"answer3\" required min=1 max=300><br>";
    }
}
