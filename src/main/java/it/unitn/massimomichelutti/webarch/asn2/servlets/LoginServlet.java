package it.unitn.massimomichelutti.webarch.asn2.servlets;

import it.unitn.massimomichelutti.webarch.asn2.beans.ErrorMsg;
import it.unitn.massimomichelutti.webarch.asn2.beans.User;
import it.unitn.massimomichelutti.webarch.asn2.beans.UserList;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;


//@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    // the number of seconds the session will last

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        HttpSession session= request.getSession();
        ServletContext context=request.getServletContext();
        boolean isLogged=false;
        boolean isAdmin=false;
        boolean error=false;
        synchronized (context) {// I always synchronize context outside of session to avoid any possible deadlock
            synchronized (session) {
                UserList users = (UserList) context.getAttribute("UserList");
                if(users==null){//if UserList is not initialized, I send an error
                    error=true;
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }else {
                    User u = users.getUserFromUsername(username);
                    if (u != null && u.getPassword().equals(password)) {
                        isLogged = true;
                        int time=Integer.parseInt(getServletConfig().getInitParameter("T"));
                        session.setMaxInactiveInterval(time);
                        u.resetScore();
                        session.setAttribute("user", u);
                        UserList active_users = (UserList) context.getAttribute("ActiveUsers");
                        active_users.addUser(u);
                        context.setAttribute("ActiveUsers",active_users);
                        if (u.getUsername().equals("admin")) {
                            // go to admin page
                            isAdmin = true;
                        }
                    }else if(u==null){
                        if(isOk(username)&&isOk(password))
                            request.setAttribute("errorMessage",new ErrorMsg("There is no user with username \""+username+"\" registered, please register your account before logging in"));
                        else
                            request.setAttribute("errorMessage",new ErrorMsg("Please fill all the fields in the form"));
                    }else{
                        request.setAttribute("errorMessage",new ErrorMsg("The password provided for the user \""+username+"\" is incorrect"));
                    }
                }
            }
        }
        if(!error) {
            if (isLogged) {
                if (isAdmin) {
                    // go to admin page
                    RequestDispatcher rd = request.getRequestDispatcher("admin/active.jsp");
                    rd.forward(request, response);
                } else {
                    // go to game home page
                    RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
                    rd.forward(request, response);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        }
    }

    private boolean isOk(String s){
        return s!=null&&(!s.equals(""))&&(!s.equals("null"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // user should only send post requests to this servlet
        response.sendError(jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
