package com.chestnut.repository;

import com.chestnut.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSRFRespository {
    public boolean updatePassword(String username, String password){
        Connection connection = JDBCTools.getConnection();
        String sql = "update users set password = ? where user = ?";
        PreparedStatement statement = null;
        boolean flag = false;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,password);
            statement.setString(2,username);
            int i =  statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
        return flag;
    }

    public boolean updatePasswordImpossible(String username, String oriPassword, String newPassword){
        Connection connection = JDBCTools.getConnection();
        String sqlQuery = "select * from users where user = ? and password = ? ";
        PreparedStatement statement = null;
        boolean flag = false;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1,username);
            statement.setString(2,oriPassword);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                CSRFRespository csrfRespository = new CSRFRespository();
                flag = csrfRespository.updatePassword(username,newPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return flag;
    }

    public static void main(String[] args) {
        CSRFRespository csrfRespository = new CSRFRespository();
        csrfRespository.updatePassword("test","test1");
    }
}
