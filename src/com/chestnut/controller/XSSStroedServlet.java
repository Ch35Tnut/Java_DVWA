package com.chestnut.controller;


import com.chestnut.entity.Guestbook;
import com.chestnut.repository.GuestbookRespository;
import com.chestnut.service.XSSService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/XSSStored")
public class XSSStroedServlet extends HttpServlet {

    private XSSService xssService = new XSSService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String message = req.getParameter("message");
        String level = null;
        List<Guestbook> list = null;
        Cookie [] cookies = req.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("level")){
                level = cookie.getValue();
            }
        }
        xssService.xssFilter(level,name,message);
        list = GuestbookRespository.findAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/XSSStored.jsp").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        xssService.clearGuestbook();
        req.getRequestDispatcher("/XSSStored.jsp").forward(req,resp);
    }
}
