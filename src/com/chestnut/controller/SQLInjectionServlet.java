package com.chestnut.controller;

import com.chestnut.entity.User;
import com.chestnut.service.SQLInjectionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sqlInjection")
public class SQLInjectionServlet extends HttpServlet {

    private SQLInjectionService sqlInjectionService = new SQLInjectionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies  = req.getCookies();
        String level = null;
        String id = req.getParameter("id");
        for(Cookie cookie :cookies){
            if (cookie.getName().equals( "level")){
                level = cookie.getValue();
            }
        }
        if (level==null){
            resp.sendRedirect("/login.jsp");
        }
        List list = sqlInjectionService.find(id,level);
        req.setAttribute("list",list);
        req.getRequestDispatcher("SQLInjection.jsp").forward(req,resp);
    }
}
