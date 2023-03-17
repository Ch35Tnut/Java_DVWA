package com.chestnut.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@WebServlet("/upload")

public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies [] = req.getCookies();
        String level = null;
        String result = null;
        for(Cookie cookie :cookies){
            if (cookie.getName().equals( "level")){
                level = cookie.getValue();
            }
        }
        try{
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for (FileItem fileItem:list){
                String fileName = fileItem.getName();
                long size = fileItem.getSize();
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                String extend = suffix.toLowerCase();
                String type = fileItem.getContentType();
                String extendList [] = new String[]{".jpeg", ".jpg", ".png"};
                switch (level){
                    case "low":
                        break;
                    case "medium":
                        if (size<10000 && (type.equals("image/jpeg") || type.equals("image/png"))){
                            result = "Success!";
                        }
                        else {
                            result = "Failure!";
                            req.setAttribute("result",result);
                            req.getRequestDispatcher("fileUpload.jsp").forward(req,resp);
                        }
                        break;
                    case "high":
                        if (size<100000 && contain(extendList,extend) && isImage(fileItem)){
                            result = "Success!";
                        }

                        else {
                            result = "Failure!";
                            req.setAttribute("result",result);
                            req.getRequestDispatcher("fileUpload.jsp").forward(req,resp);
                        }
                        break;
                    case "impossible":
                        if (size<100000 && (type.equals("image/jpeg") || type.equals("image/png") && isImage(fileItem))){
                        String path = req.getServletContext().getRealPath("file/");
                        BufferedImage image = ImageIO.read(fileItem.getInputStream());
                        ImageIO.write(image,"jpg",new File(path+"sdflj.jpg"));
                        req.setAttribute("result",result);
                        req.getRequestDispatcher("fileUpload.jsp").forward(req,resp);

                    }


                }
                String path = req.getServletContext().getRealPath("file/")+fileName;
                InputStream inputStream = fileItem.getInputStream();
                OutputStream outputStream = new FileOutputStream(path);
                int temp = 0;
                while ((temp = inputStream.read())!=-1){
                    outputStream.write(temp);
                }
                outputStream.close();
                inputStream.close();
                req.setAttribute("result",result);
                req.getRequestDispatcher("fileUpload.jsp").forward(req,resp);
//                System.out.println(fileName+":"+size+"Byte");

            }
        } catch (FileUploadException e){
            e.printStackTrace();
        }
    }
    public static boolean contain(String[] arr, String targetValue) {
        for(String s: arr){
            if(s.equals(targetValue)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isImage(FileItem fileItem) throws IOException{
        BufferedImage  bufferedImage = ImageIO.read(fileItem.getInputStream());
        if (bufferedImage == null){
            return false;
        }
        return true;
    }
}
