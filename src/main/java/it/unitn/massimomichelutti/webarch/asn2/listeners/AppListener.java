package it.unitn.massimomichelutti.webarch.asn2.listeners;

import it.unitn.massimomichelutti.webarch.asn2.beans.CapitalList;
import it.unitn.massimomichelutti.webarch.asn2.beans.User;
import it.unitn.massimomichelutti.webarch.asn2.beans.UserList;
import it.unitn.massimomichelutti.webarch.asn2.beans.WorldMap;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;

@WebListener
public class AppListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public AppListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        try {
            // Load Users
            FileInputStream f = new FileInputStream(new File("users.txt"));
            ObjectInputStream o = new ObjectInputStream(f);
            UserList userlist=(UserList) o.readObject();
            o.close();
            f.close();
            // adding default users (for first time opening the app)
            if(userlist==null) {
                // This code should never execute after the first time tha pp is launched, but
                // it happened that Tomcat crashed while starting and when that happened
                // I lost for some reason the text file with all the users,
                // it happened only twice, and I wasn't able to replicate it consistently
                userlist = new UserList();
                userlist.loadDefaultUsers();
            }
            sce.getServletContext().setAttribute("UserList",userlist);
            // Create Active Users List
            UserList users=new UserList();
            sce.getServletContext().setAttribute("ActiveUsers",users);
            // Load game structures
            WorldMap worldmap=new WorldMap();
            worldmap.loadworld();
            sce.getServletContext().setAttribute("worldmap",worldmap);
            CapitalList capitals=new CapitalList();
            capitals.loadCapitals();
            sce.getServletContext().setAttribute("CapitalList",capitals);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        try {
            FileOutputStream f = new FileOutputStream(new File("users.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            UserList userlist=(UserList) sce.getServletContext().getAttribute("UserList");
            o.writeObject(userlist);
            o.close();
            f.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        User u=(User)se.getSession().getAttribute("user");
        if(u!=null){
            UserList active_users=(UserList)se.getSession().getServletContext().getAttribute("ActiveUsers");
            active_users.removeUser(u);
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
