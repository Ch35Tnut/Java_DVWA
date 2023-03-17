package com.chestnut.repository;

import com.chestnut.entity.Guestbook;
import com.chestnut.utils.JDBCTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestbookRespository {

    public void insert(String name,String message){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "insert into guestbook (comment, name) values (?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,message);
            statement.setString(2,name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
    }


    public static List findAll(){
         List<Guestbook> guestbookList = new ArrayList<>();
         Connection connection = null;
         PreparedStatement statement = null;
         ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "select * from guestbook";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                guestbookList.add(new Guestbook(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }

        return guestbookList;
    }

    public void clear(){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "delete from guestbook";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
    }


    public static void main(String[] args) {
        GuestbookRespository guestbookRespository = new GuestbookRespository();
        guestbookRespository.insert("sdkljf","nicaizheshisha");
        guestbookRespository.clear();
        int i = 0;
    }
}
