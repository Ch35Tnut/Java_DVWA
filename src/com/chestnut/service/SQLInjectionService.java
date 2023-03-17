package com.chestnut.service;

import com.chestnut.entity.User;
import com.chestnut.repository.SQLInjectionRespository;

import java.util.List;

public class SQLInjectionService {
    private SQLInjectionRespository sqlInjectionRespository = new SQLInjectionRespository();

    public List find (String id,String level){
        List list = sqlInjectionRespository.find(id,level);
        int i =0;
        return list;
    }
}
