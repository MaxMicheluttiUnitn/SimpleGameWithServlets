package it.unitn.massimomichelutti.webarch.asn2.filters;

import it.unitn.massimomichelutti.webarch.asn2.beans.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    FilterConfig filterconfig;

    public void init(FilterConfig config) throws ServletException {
        filterconfig=config;
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpSession session=req.getSession();
        if(session!=null){
            User user=(User)session.getAttribute("user");
            if ((user==null)||(user.getUsername().equals(""))||(user.getUsername().equals("null"))){
                // request is not allowed to access url -> forward to login.jsp
                RequestDispatcher rd=req.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }else{
                chain.doFilter(request, response);
            }
        }else {
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }
}
