package it.unitn.massimomichelutti.webarch.asn2.beans;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private  String password;
    int score;

    public User(){
        username="";
        password="";
        score=0;
    }
    public User(String un, String pw){
        username=un;
        password=pw;
        score=0;
    }

    /*
     *  This function returns the username of the User
     */
    public String getUsername(){
        return username;
    }
    /*
     *  This function returns the password of the User
     */
    public String getPassword(){
        return password;
    }
    /*
     *  This function returns the score of the User
     */
    public int getScore(){
        return score;
    }
    /*
     *  This function add points to the score of the User
     *  To subtract points, just pass a negative value to this function
     *  Points cannot go lower than 0, if a score update
     *  pushes the score below 0, the score is reset to 0
     */
    public void addPoints(int pt){
        score+=pt;
        if(score<0)
            score=0;
    }

    /*
     * resets the score to 0
     */
    public void resetScore(){
        score=0;
    }

    @Override
    public String toString(){
        return "<li>"+username+": "+score+" points</li>";
    }

    @Override
    public boolean equals(Object o){
        if(o==null)
            return false;
        if(o==this)
            return true;
        if(!(o instanceof User))
            return false;
        User u=(User)o;
        return username.equals(u.getUsername());
    }
}
