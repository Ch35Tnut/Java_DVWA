package com.chestnut.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;

public class CommandService {

    public static String ping (String ip,String level){
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        String result = null;
        switch (level){
            case "low":
               break;
            case "medium":
                ip = ip.replace("&&","").replace(";","");
                break;
            case "high":
                ip = ip.replace("&","").replace(";","").replace("| ","").replace("-","").replace("$","").replace("(","").replace(")","").replace("`","").replace("||","");
                break;
            case "impossible":
                String [] ipList = ip.split("\\.");
                for (String item:ipList){
                    if(!isInteger(item)){
                        result = "ERROR！";
                        return result;
                    }
                }
        }
        try {
            process = runtime.exec("ping "+ip);
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line =reader.readLine())!=null){
                result += line+"<br>";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
