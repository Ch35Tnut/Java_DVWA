package com.chestnut.repository;

import com.chestnut.entity.User;
import com.chestnut.utils.JDBCTools;
import org.apache.commons.lang3.StringEscapeUtils;
import java.util.Date;

import java.sql.*;
import java.util.Timer;

public class BruteRespository {

    private Integer TOTAL_FILED_LOGIN = 3;
    private Integer LOCKOUT_TIME = 15*60*1000;

    public boolean BruteLow(String username,String password){
        boolean flag = false;
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from users where user='"+username+"' and password = '"+password+"'";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return flag;
    }

    public boolean BruteMedium(String username,String password){
        boolean flag = false;
        Connection connection = JDBCTools.getConnection();
        username = JDBCTools.escapeSql(username);
        password = JDBCTools.escapeSql(password);
        String sql = "select * from users where user='"+username+"' and password = '"+password+"'";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return flag;
    }

    public boolean BruteHigh(String username,String password){
        boolean flag = false;
        Connection connection = JDBCTools.getConnection();
        username = JDBCTools.escapeSql(username);
        password = JDBCTools.escapeSql(password);
        String sql = "select * from users where user=?";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Timestamp lastLogin = resultSet.getTimestamp(7);
                Integer failedLogin = resultSet.getInt(8);
                String password_sql = resultSet.getString(5);
                Long now = new java.util.Date().getTime();
                Long lastLoginLon = new java.util.Date(lastLogin.getTime()).getTime();
                if (now-lastLoginLon<LOCKOUT_TIME && failedLogin > 3)
                {
                    flag = false;
                    break;
                }
                if (password.equals(password_sql)){
                    if(now-lastLoginLon > LOCKOUT_TIME){
                        flag = true;
                    }
                    else if (failedLogin<TOTAL_FILED_LOGIN){
                        flag = true;
                    }
                }
                else {
                    flag = false;
                    if (now - lastLoginLon > LOCKOUT_TIME) {
                        String sqlUpdate = "update users set failed_login = 0  where users = ?" ;
                        statement = connection.prepareStatement(sqlUpdate);
                        statement.setString(1,username);
                        statement.executeUpdate();
                    }
                    else {
                        String sqlUpdate = "update users set failed_login=failed_login+1 where users = ?" ;
                        statement = connection.prepareStatement(sqlUpdate);
                        statement.setString(1,username);
                        statement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return flag;
    }

    public static void main(String[] args) {
//        BruteRespository bruteRespository  = new BruteRespository();
//        boolean flag = bruteRespository.BruteLow("admin","21232f297a57a5a743894a0e4a801fc3");
//        int i = 0;
    }
}
