package com.chestnut.service;

import com.chestnut.entity.User;
import com.chestnut.repository.UserRespository;
import com.chestnut.utils.MD5Tools;

public class LoginService {
    private UserRespository userRespository = new UserRespository();

    public User login(String username, String password){
        String md5_password = MD5Tools.getMD5Str(password);
        User user = null;
        user = userRespository.login(username,md5_password);
        return user;
    }
}
