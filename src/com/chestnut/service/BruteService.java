package com.chestnut.service;

import com.chestnut.repository.BruteRespository;
import com.chestnut.utils.JWTTools;
import com.chestnut.utils.MD5Tools;

public class BruteService {
    private BruteRespository bruteRespository = new BruteRespository();

    public boolean Brute(String username,String password,String level,String token){
        String md5_password = MD5Tools.getMD5Str(password);
        boolean flag = false;
        switch (level){
            case "low":
                flag = bruteRespository.BruteLow(username,md5_password);
                break;
            case "medium":
                flag = bruteRespository.BruteMedium(username,md5_password);
                break;
            case "high":
                flag = (JWTTools.verify(token)) && bruteRespository.BruteMedium(username,md5_password);
                break;
            case "impossible":
                flag = (JWTTools.verify(token)) && bruteRespository.BruteHigh(username,md5_password);
        }
        return flag;
    }
}
