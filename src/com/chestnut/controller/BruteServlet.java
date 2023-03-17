package com.chestnut.controller;

import com.chestnut.service.BruteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/brute")
public class BruteServlet extends HttpServlet {

    private BruteService bruteService = new BruteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String token = req.getParameter("user_token");
        Cookie [] cookies  = req.getCookies();
        String level = null;
        boolean flag = false;
        for(Cookie cookie :cookies){
            if (cookie.getName().equals( "level")){
                level = cookie.getValue();
            }
        }
        if (level==null){
            resp.sendRedirect("/login.jsp");
        }
        else {
            flag = bruteService.Brute(username,password,level,token);
        }
        if (flag){
            req.setAttribute("result","Success!");
            req.getRequestDispatcher("/brute.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("result","Failure!");
            req.getRequestDispatcher("brute.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String token = req.getParameter("user_token");
        Cookie [] cookies  = req.getCookies();
        String level = null;
        boolean flag = false;
        for(Cookie cookie :cookies){
            if (cookie.getName().equals( "level")){
                level = cookie.getValue();
            }
        }
        if (level==null){
            resp.sendRedirect("/login.jsp");
        }
        else {
            flag = bruteService.Brute(username,password,level,token);
        }
        if (flag){
            req.setAttribute("result","Success!");
            req.getRequestDispatcher("/brute.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("result","Failure!");
            req.getRequestDispatcher("brute.jsp").forward(req,resp);
        }

    }
}
