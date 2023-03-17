package com.chestnut.controller;

import com.chestnut.service.CSRFService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/csrf_imp")
public class CSRFImpossibleServlet extends HttpServlet {

    private CSRFService csrfService = new CSRFService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oriPassword = req.getParameter("ori_password");
        String newPassword = req.getParameter("new_password");
        String conPassword = req.getParameter("con_password");
        String token = req.getParameter("user_token");
        Cookie[] cookies  = req.getCookies();
        String referer = null;
        referer = req.getHeader("referer");
        String level = null;
        String username = null;
        String result = null;
        for(Cookie cookie :cookies){
            if (cookie.getName().equals( "level")){
                level = cookie.getValue();
            }
            if (cookie.getName().equals("username")){
                username = cookie.getValue();
            }
        }
        if (newPassword.equals(conPassword)){
            if (csrfService.updatePassword(username, conPassword,level,token,referer)) {
                result= "Success!";
            }
        }
    }
}
