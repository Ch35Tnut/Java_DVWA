package com.chestnut.controller;

import com.chestnut.entity.User;
import com.chestnut.service.LoginService;
import com.chestnut.utils.JWTTools;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String level = req.getParameter("level");
        resp.addCookie(new Cookie("level",level));
        resp.addCookie(new Cookie("username",username));
        HttpSession session = req.getSession();
        if (level.equals("impossible")){
            session.setAttribute("brute_methods","post");
        }
        else{
            session.setAttribute("brute_methods","get");
        }

        User user = loginService.login(username,password);
        // 登陆成功
        if (user!=null){
            String token = JWTTools.sign(username,password);
            if (token!=null){
                session.setAttribute("user_token",token);
            }
            resp.sendRedirect("/index.jsp");
        }
        else{
            resp.sendRedirect("/login.jsp");
        }

    }
}
