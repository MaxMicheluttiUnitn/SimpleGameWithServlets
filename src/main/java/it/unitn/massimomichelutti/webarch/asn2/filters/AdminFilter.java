package it.unitn.massimomichelutti.webarch.asn2.filters;

import it.unitn.massimomichelutti.webarch.asn2.beans.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if ((user == null) || !(user.getUsername().equals("admin"))) {
                HttpServletResponse res=(HttpServletResponse)response;
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            HttpServletResponse res=(HttpServletResponse)response;
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    public void destroy() {
    }
}
