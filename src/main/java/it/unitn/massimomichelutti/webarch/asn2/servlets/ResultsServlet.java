package it.unitn.massimomichelutti.webarch.asn2.servlets;

import it.unitn.massimomichelutti.webarch.asn2.beans.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.LinkedList;

//@WebServlet(name = "ResultsServlet", value = "/ResultsServlet")
public class ResultsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String par1=request.getParameter("answer1");
        String par2=request.getParameter("answer2");
        String par3=request.getParameter("answer3");
        if(par1==null || par2==null || par3==null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else {
            try {
                int val1 = Integer.parseInt(par1) - 1;
                int val2 = Integer.parseInt(par2) - 1;
                int val3 = Integer.parseInt(par3) - 1;
                ServletContext context = request.getServletContext();
                HttpSession session = request.getSession();
                boolean error=false;
                synchronized (context) {
                    synchronized (session) {
                        Game game = (Game) session.getAttribute("game");
                        WorldMap worldmap = (WorldMap) context.getAttribute("worldmap");
                        if (worldmap == null) { // this should never run, but I keep it as a recovery from some weird errors
                            worldmap = new WorldMap();
                            worldmap.loadworld();
                            context.setAttribute("worldmap", worldmap);
                        }
                        User user = (User) session.getAttribute("user");
                        if (game != null) { // if game is null I just go back home (might happen if user sends same request more than once)
                            if (user == null) {// should never happen (filter should prevent it), but let's be careful anyway
                                error=true;
                                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            } else {
                                // notice there is no issue when val1 or val2 or val3 are OutOfBounds for worldmap
                                // because getCountry returns null and then equals just returns false and control
                                // goes to the else block
                                if (game.getFirst().equals(worldmap.getCountry(val1)) &&
                                        game.getSecond().equals(worldmap.getCountry(val2)) &&
                                        game.getThird().equals(worldmap.getCountry(val3))) {
                                    user.addPoints(3);
                                } else {
                                    user.addPoints(-1);
                                }
                                session.setAttribute("user",user);
                                // resetting game so sending more than once the same request doesn't give points more than once
                                session.removeAttribute("game");
                            }
                        }
                    }
                }
                if(!error) {
                    RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
                    rd.forward(request, response);
                }
            }catch(NumberFormatException e){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
