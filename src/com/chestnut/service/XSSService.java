package com.chestnut.service;

import com.chestnut.entity.Guestbook;
import com.chestnut.repository.GuestbookRespository;
import com.chestnut.utils.JDBCTools;

import java.util.List;

public class XSSService {

    private GuestbookRespository guestbookRespository = new GuestbookRespository();

    public String xssFilter(String level, String name) {
        String result = null;
        switch (level) {
            case "low":
                result = name;
                break;
            case "medium":
                result = name.replaceAll("<script>", "");
                break;
            case "high":
                result = name.replaceAll("/<(.*)s(.*)c(.*)r(.*)i(.*)p(.*)t/i",name);
                break;
            case "impossible":
                result = htmlEncode(name);
                break;
        }
        return result;
    }

    public static String htmlEncode(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '<':
                    buffer.append("&lt;");
                    break;
                case '>':
                    buffer.append("&gt;");
                    break;
                case '&':
                    buffer.append("&amp;");
                    break;
                case '"':
                    buffer.append("&quot;");
                    break;
                case 10:
                case 13:
                    break;
                default:
                    buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }

    public List xssFilter(String level, String name, String message){
        List<Guestbook> list = null;
        message = message.trim();
        name = name.trim();
        switch (level) {
            case "low":
                guestbookRespository.insert(name, message);
                break;
            case "medium":
                message = JDBCTools.escapeSql(message);
                message = htmlEncode(message);
                name = name.replace("<script>", "");
                guestbookRespository.insert(name, message);
                break;
            case "high":
                message = htmlEncode(message);
                name = name.replace("/<(.*)s(.*)c(.*)r(.*)i(.*)p(.*)t/i", "");
                guestbookRespository.insert(name, message);
                break;
            case "impossible":
                message = htmlEncode(message);
                name = htmlEncode(name);
                guestbookRespository.insert(name,message);
                break;
        }
        list = GuestbookRespository.findAll();
        return list;
    }
    public void clearGuestbook(){
        guestbookRespository.clear();
    }

//    public static String stripslashes(String str){
//        return str.replace("\\","");
//    }
//
//    public static String escepe(String str){
//        return str.replace("\\x00","\\\\x00").replace("\n","\\\\n").replace("\r","\\\\r").replace("\'","\\\\'").replace("\\x1a", "\\\\x1a");
//    }



}
