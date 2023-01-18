package it.unitn.massimomichelutti.webarch.asn2.beans;

import java.io.Serializable;

public class Country implements Serializable {
    private String name;

    public Country(){
        name="";
    }
    public Country(String s){
        name=s;
    }
    /*
     * this function returns the name of the country
     */
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "<img src=\"flags/"+name.toLowerCase()+".jpg\" />";
        //return "<img src=\"bandiere/"+name+".jpg\" />";
    }

    @Override
    public boolean equals(Object o){
        if(o==null)
            return false;
        if(o==this)
            return true;
        if(!(o instanceof Country))
            return false;
        Country c=(Country) o;
        return name.equals(c.getName());
    }
}
