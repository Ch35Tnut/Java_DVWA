package com.chestnut.repository;

import com.chestnut.entity.User;
import com.chestnut.utils.JDBCTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLInjectionRespository {

    private Connection connection = JDBCTools.getConnection();
    private ResultSet resultSet = null;
    private PreparedStatement statement = null;
//    List<User> list = new ArrayList<>();
    List mapList = new ArrayList();

    public List find(String id,String level){
        try {
            String sql = null;
            ResultSetMetaData metaData = resultSet.getMetaData();
            Integer columnCount = metaData.getColumnCount();
            Map rowData = new HashMap();
            switch (level){
                case "low":
                        sql = "select * from users where user_id = '"+ id +"'";
                        statement = connection.prepareStatement(sql);
                        resultSet = statement.executeQuery();
                        metaData = resultSet.getMetaData();
                        columnCount = metaData.getColumnCount();
                        rowData = new HashMap();
                        while (resultSet.next()){
                            for (int i = 1; i <= columnCount; i++){
                                rowData.put(metaData.getColumnName(i),resultSet.getObject(i));
                            }
                            mapList.add(rowData);
                            rowData = new HashMap();
    //                        list.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getTimestamp(7),resultSet.getInt(8)));
                        }
                        break;
                case "medium":
                    id = JDBCTools.escapeSql(id);
                    sql = "select * from users where user_id = '"+ id +"'";
                    statement = connection.prepareStatement(sql);
                    resultSet = statement.executeQuery();
                    metaData = resultSet.getMetaData();
                    columnCount = metaData.getColumnCount();
                    rowData = new HashMap();
                    while (resultSet.next()){
                        for (int i = 1; i <= columnCount; i++){
                            rowData.put(metaData.getColumnName(i),resultSet.getObject(i));
                        }
                        mapList.add(rowData);
                        rowData = new HashMap();
    //                        list.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getTimestamp(7),resultSet.getInt(8)));
                    }
                    break;
                case "high":
                    id = JDBCTools.escapeSql(id);
                    sql = "select * from users where user_id = '"+ id +"' limit 1";
                    statement = connection.prepareStatement(sql);
                    resultSet = statement.executeQuery();
                    metaData = resultSet.getMetaData();
                    columnCount = metaData.getColumnCount();
                    rowData = new HashMap();
                    while (resultSet.next()){
                        for (int i = 1; i <= columnCount; i++){
                            rowData.put(metaData.getColumnName(i),resultSet.getObject(i));
                        }
                        mapList.add(rowData);
                        rowData = new HashMap();
                        //                        list.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getTimestamp(7),resultSet.getInt(8)));
                    }
                    break;
                case "impossible":
                    id = JDBCTools.escapeSql(id);
                    sql = "select * from users where user_id = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1,id);
                    resultSet = statement.executeQuery();
                    metaData = resultSet.getMetaData();
                    columnCount = metaData.getColumnCount();
                    rowData = new HashMap();
                    while (resultSet.next()){
                        for (int i = 1; i <= columnCount; i++){
                            rowData.put(metaData.getColumnName(i),resultSet.getObject(i));
                        }
                        mapList.add(rowData);
                        rowData = new HashMap();
                        //                        list.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getTimestamp(7),resultSet.getInt(8)));
                    }
                    if (mapList.size()>1){
                        mapList.clear();
                    }
                    break;
                }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return mapList;
    }


    public static void main(String[] args) {
        SQLInjectionRespository sqlInjectionRespository = new SQLInjectionRespository();
        List list = sqlInjectionRespository.find("3' union select 1,2,database(),3,4,5,6,7 #","low");
        int i = 0;
    }
}
