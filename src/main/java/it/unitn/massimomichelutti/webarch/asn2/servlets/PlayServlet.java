package it.unitn.massimomichelutti.webarch.asn2.servlets;

import it.unitn.massimomichelutti.webarch.asn2.beans.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

//@WebServlet(name = "PlayServlet", value = "/PlayServlet")
public class PlayServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ServletContext context = request.getServletContext();
        WorldMap worldmap=null;
        synchronized (context) {
            worldmap = (WorldMap) context.getAttribute("worldmap");
            //CapitalList capitals = (CapitalList) context.getAttribute("CapitalList");
            if (worldmap == null) { // this should never run, but I keep it as a recovery from some weird errors
                worldmap = new WorldMap();
                worldmap.loadworld();
                context.setAttribute("worldmap", worldmap);
            }
            /*if (capitals == null) { // this should never run, but I keep it as a recovery from some weird errors
                capitals = new CapitalList();
                capitals.loadCapitals();
                context.setAttribute("CapitalList", capitals);
            }*/
        }
        //generate 3 different random flags
        Random rand = new Random();
        int max=worldmap.size();
        int x=rand.nextInt(max);
        int y=rand.nextInt(max);
        while(x==y){
            y=rand.nextInt(max);
        }
        int z=rand.nextInt(max);
        while(z==y || z==x){
            z=rand.nextInt(max);
        }
        //generate a game and add it to the session
        HttpSession session = request.getSession();
        synchronized (session) {
            Game game=(Game)session.getAttribute("game");
            //if there is no game for the user, the game is set
            if(game==null || game.getFirst().equals(new Country())) {
                game = new Game(worldmap.getCountry(x), worldmap.getCountry(y), worldmap.getCountry(z));
                session.setAttribute("game", game);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("game.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
