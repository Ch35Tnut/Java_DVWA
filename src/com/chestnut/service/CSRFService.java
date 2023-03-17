package com.chestnut.service;

import com.chestnut.repository.CSRFRespository;
import com.chestnut.utils.JWTTools;
import com.chestnut.utils.MD5Tools;

public class CSRFService {

    private CSRFRespository csrfRespository = new CSRFRespository();

    public boolean updatePassword(String username,String password,String level, String token,String referer){
        boolean flag = true;
        String md5_password = MD5Tools.getMD5Str(password);
        switch (level){
            case "low":
                flag =  csrfRespository.updatePassword(username,md5_password);
                break;
            case "medium":
                if (referer.contains("localhost")){
                    flag = csrfRespository.updatePassword(username,md5_password);
                }
                break;
            case "high":
                if (JWTTools.verify(token)){
                    flag = csrfRespository.updatePassword(username,md5_password);
                }
                break;

        }

        return flag;
    }
    public  boolean updatePasswordImpossible(String username, String oriPassword, String newPassword){
        boolean flag = true;
        String md5OriPassword = MD5Tools.getMD5Str(oriPassword);
        String md5NewPassword = MD5Tools.getMD5Str(newPassword);
        flag = csrfRespository.updatePasswordImpossible(username,md5OriPassword,md5OriPassword);
        return flag;
    }

}
