package com.chestnut.controller;

import com.chestnut.service.CommandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/command")
public class CommnandServlet extends HttpServlet {

    private CommandService commandService = new CommandService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip = req.getParameter("ip");
        Cookie[] cookies  = req.getCookies();
        String level = null;
        String result = null;
        for(Cookie cookie :cookies){
            if (cookie.getName().equals( "level")){
                level = cookie.getValue();
            }
        }
        if (level==null){
            resp.sendRedirect("/login.jsp");
        }
        else {
           result =commandService.ping(ip,level);
        }
        req.setAttribute("result",result);
        req.getRequestDispatcher("/command.jsp").forward(req,resp);
    }
}
