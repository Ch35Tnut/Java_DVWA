package com.chestnut.utils;

import com.sun.jmx.snmp.Timestamp;


public class TokenTools {

    private static  final String SCREAT = "MYTOKEN";

    public String getToken(){
        Timestamp d = new Timestamp();
        String time = d.toString();
        String token = MD5Tools.getMD5Str(time+SCREAT);
        return token;
    }
}
