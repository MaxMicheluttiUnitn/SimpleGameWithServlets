package it.unitn.massimomichelutti.webarch.asn2.beans;

import java.io.Serializable;

public class Capital implements Serializable {
    String name;

    public Capital(){
        name="";
    }
    public Capital(String n){
        name=n;
    }
    /*
     * returns the name of the capital
     */
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "<li>"+name+"</li>";
    }

}
