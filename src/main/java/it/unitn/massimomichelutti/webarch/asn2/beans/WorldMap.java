package it.unitn.massimomichelutti.webarch.asn2.beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WorldMap implements Serializable {
    LinkedList<Country> m;

    public WorldMap(){
        m=new LinkedList<>();
    }

    /*
     * This function initializes the structure with default values
     */
    public void loadworld(){
        /*m.add(new Country("AF"));
        m.add(new Country("AL"));
        m.add(new Country("DZ"));
        m.add(new Country("AS"));
        m.add(new Country("AD"));
        m.add(new Country("AO"));
        m.add(new Country("AI"));
        m.add(new Country("AG"));
        m.add(new Country("AR"));
        m.add(new Country("AM"));
        m.add(new Country("AW"));
        m.add(new Country("AU"));
        m.add(new Country("AT"));
        m.add(new Country("AZ"));*/
        try {
            File myObj = new File("D:\\intelliJ_IDEA\\Assign2_2022\\src\\main\\webapp\\WEB-INF\\world_codes.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                m.add(new Country(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        /*m.add(new Country("Algeria"));
        m.add(new Country("Armenia"));
        m.add(new Country("Chad"));
        m.add(new Country("Czech_Republic"));
        m.add(new Country("Djibouti"));
        m.add(new Country("Gabon"));
        m.add(new Country("Indonesia"));
        m.add(new Country("Lithuania"));
        m.add(new Country("Malta"));
        m.add(new Country("Ukraine"));*/
    }

    /*
     * This function inserts a new Country @param c at the end of the array
     * Returns true if
     */
    public boolean addCountry(Country c){
        for(Country x: m){
            if(x.equals(c))
                return false;
        }
        return m.add(c);
    }

    /*
     * This function returns the size of m
     */
    public int size(){
        return m.size();
    }

    /*
     * returns a country at index @param id, null if index is out of bounds
     */
    public Country getCountry(int id){
        if(id>=0 && m.size()>id)
            return m.get(id);
        return null;
    }

}
