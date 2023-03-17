package com.chestnut.controller;

import com.chestnut.service.XSSService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/XSSReflected")
public class XSSReflectedServlet extends HttpServlet {

    private XSSService xssService = new XSSService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String level = null;
        String name = req.getParameter("name");
        String result = null;
        Cookie [] cookies = req.getCookies();
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("level")){
                level = cookie.getValue();
            }
        }
        result = xssService.xssFilter(level,name);
        req.setAttribute("result", "Hello "+result);
        req.getRequestDispatcher("/XSSReflected.jsp").forward(req,resp);
    }
}
