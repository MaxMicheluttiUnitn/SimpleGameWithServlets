package it.unitn.massimomichelutti.webarch.asn2.servlets;

import it.unitn.massimomichelutti.webarch.asn2.beans.ErrorMsg;
import it.unitn.massimomichelutti.webarch.asn2.beans.User;
import it.unitn.massimomichelutti.webarch.asn2.beans.UserList;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String password_repeat=request.getParameter("password_repeat");
        if(isOk(password) && isOk(password_repeat) && isOk(username) && password_repeat.equals(password)) {
            boolean isNew = false;
            boolean error= false;
            ServletContext context=request.getServletContext();
            synchronized (context) {
                UserList users=(UserList)context.getAttribute("UserList");
                if(users==null){//if UserList is not initialized, I send an error
                    error=true;
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }else{
                    User newuser = new User(username, password);
                    isNew = users.addUser(newuser);
                    context.setAttribute("UserList",users);
                }
            }
            if(!error) {
                if (isNew) {// if registration is successful the user is redirected to the login page
                    request.setAttribute("errorMessage",new ErrorMsg("Registration was successful, now log in to play!"));
                    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                } else {// if registration is not successful the user is redirected to the registration page
                    request.setAttribute("errorMessage",new ErrorMsg("Username \""+username+"\" is already taken"));
                    RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
                    rd.forward(request, response);
                }
            }
        }else if(!(isOk(password) && isOk(password_repeat) && isOk(username))){
            request.setAttribute("errorMessage",new ErrorMsg("Please fill all the fields in the form"));
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            rd.forward(request, response);
        }else{
            request.setAttribute("errorMessage",new ErrorMsg("Passwords do not match"));
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            rd.forward(request, response);
        }
    }

    private boolean isOk(String data){
        return data!=null && !data.equals("") && !data.equals("null");
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
