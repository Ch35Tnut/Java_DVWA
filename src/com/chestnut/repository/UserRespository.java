package com.chestnut.repository;

import com.chestnut.entity.User;
import com.chestnut.utils.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRespository {

    public User login(String username,String password){
        User user = null;
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from users where user= ? and password = ?";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getTimestamp(7),resultSet.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return user;
    }

    public static void main(String[] args) {
        User user = new UserRespository().login("admin","21232f297a57a5a743894a0e4a801fc3");
        int i = 0;
    }
}
