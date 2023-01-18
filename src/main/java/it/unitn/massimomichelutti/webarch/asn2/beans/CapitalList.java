package it.unitn.massimomichelutti.webarch.asn2.beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;

public class CapitalList implements Serializable {
    LinkedList<Capital> capitals;
    public CapitalList(){
        capitals=new LinkedList<>();
    }
    /*
     *  returns the capital at the given index, if the given is out of bounds returns null
     */
    public Capital getCapital(int id){
        if(capitals.size()>id && id>=0)
            return capitals.get(id);
        return null;
    }
    /*
     *  adds a new capital to the list
     */
    public void add(Capital c){
        capitals.add(c);
    }

    /*
     * Load CapitalList with default values
     */
    public void loadCapitals(){
        LinkedList list=capitals;
        /*list.add(new Capital("Kabul"));
        list.add(new Capital("Tirana"));
        list.add(new Capital("Algiers"));
        list.add(new Capital("Pago Pago"));
        list.add(new Capital("Andorra la Vella"));
        list.add(new Capital("Luanda"));
        list.add(new Capital("The Valley"));
        list.add(new Capital("Saint John's"));
        list.add(new Capital("Buenos Aires"));
        list.add(new Capital("Yerevan"));
        list.add(new Capital("Oranjestad"));
        list.add(new Capital("Canberra"));
        list.add(new Capital("Vienna"));
        list.add(new Capital("Baku"));*/
        try {
            File myObj = new File("D:\\intelliJ_IDEA\\Assign2_2022\\src\\main\\webapp\\WEB-INF\\world_capitals.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data=data.replace('_',' ');
                list.add(new Capital(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        /*list.add(new Capital("Algiers"));
        list.add(new Capital("Yeravan"));
        list.add(new Capital("N'Djamena"));
        list.add(new Capital("Prague"));
        list.add(new Capital("Djibouti"));
        list.add(new Capital("Libreville"));
        list.add(new Capital("Jakarta"));
        list.add(new Capital("Vilnius"));
        list.add(new Capital("La Valletta"));
        list.add(new Capital("Kiev"));*/
    }

    @Override
    public String toString(){
        String res="<ol>";
        for(Capital c: capitals){
            res+=c.toString();
        }
        return res+"</ol>";
    }
}
