package it.unitn.massimomichelutti.webarch.asn2.beans;

import java.io.Serializable;
import java.util.LinkedList;

public class UserList implements Serializable {
    LinkedList<User> userlist;
    public UserList(){
        userlist=new LinkedList<User>();
    }
    /*
     *  This function retrieves the informations about a User with username equal to @param name
     *  and returns the User. If no User with such username is found, this function returns null.
     */
    public User getUserFromUsername(String name){
        for(User u: userlist){
            if(u.getUsername().equals(name))
                return u;
        }
        return null;
    }
    /*
     *  This function adds a new User @param user to the UserList.
     *  This function returns a boolean that is true if the
     *  insertion was successful, false otherwise
     */
    public boolean addUser(User user){
        // cannot add user that is already in the system
        for(User u: userlist){
            if(u.equals(user))
                return false;
        }
        userlist.add(user);
        return true;
    }
    /*
     * This function loads the default users
     */
    public void loadDefaultUsers(){
        User admin=new User("admin","nimda");
        addUser(admin);
        User max=new User("max","max");
        addUser(max);
        User luke=new User("luke","luke");
        addUser(luke);
        User valery=new User("valery","valery");
        addUser(valery);
        User alice=new User("alice","alice");
        addUser(alice);
        User fabian=new User("fabian","fabian");
        addUser(fabian);
        User kate=new User("kate","kate");
        addUser(kate);
    }

    /*
     *  This function removes a User @param user to the UserList.
     *  This function returns a boolean that is true if the
     *  removal was successful (@param user was present in the UserList
     *  and is now removed), false otherwise
     */
    public boolean removeUser(User user){
        // cannot add user that is already in the system
        if(user==null)
            return false;
        return userlist.remove(user);
    }

    @Override
    public String toString(){
        String res="Users:<br><ul>";
        for(User u: userlist){
            res+=u.toString();
        }
        return res+"</ul>";
    }
}
