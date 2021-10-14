package org.example.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class UserDao {
    @Autowired
    private Connection connection;

    public void printUsers(){
        try{
            String query = "Select * from USER where NAME = ?";
            PreparedStatement prdStmt = connection.prepareStatement(query);

            prdStmt.setString(1, "daksh");

            ResultSet rs  = prdStmt.executeQuery();

            while(rs.next()){
                System.out.println("User name: " + rs.getString(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("location: " + rs.getString(3));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
