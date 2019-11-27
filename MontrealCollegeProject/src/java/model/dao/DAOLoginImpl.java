package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;

public class DAOLoginImpl implements DAOLogin {
    
    @Override
    public String userPass(String username, String password) throws SQLException {
    Connection conn = DatabaseConnection.getConnection();
        
    int num1 = 0;    
    String sql1="select count(*) as theCount from users where USERNAME=? and PASSWORD=?";
    PreparedStatement st1 = conn.prepareStatement(sql1);
    st1.setString(1, username);
    st1.setString(2, password);
    ResultSet result1 = st1.executeQuery();
    while(result1.next()){
        num1 = (result1.getInt("theCount"));
    }   
    return num1==1?"T":"F";
    }

@Override
    public String userRole(String username) throws SQLException {
    Connection conn = DatabaseConnection.getConnection();
       
    String rl="";
    List<String> ar=new ArrayList();
    String sql="select role_name  from user_roles where USERNAME=?";
    PreparedStatement st1 = conn.prepareStatement(sql);
    st1.setString(1, username);
    ResultSet result = st1.executeQuery();
    while(result.next()){
//        ar.add(result.getString("roles"));
        rl=(result.getString("role_name"));
    }   
    return rl;
    }        
}
