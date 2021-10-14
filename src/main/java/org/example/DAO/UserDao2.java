package org.example.DAO;


import org.example.Entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Component
public class UserDao2 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Properties properties;

    public void printUsers(){
        String query = "Select * from USER where NAME = '"+properties.getProperty("name")+"'";
        List<UserEntity> objList = jdbcTemplate.query(query, new RowMapper<UserEntity>(){
            @Override
            public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException{
                UserEntity userEntity = new UserEntity();
                userEntity.setUserName(rs.getString("USERNAME"));
                userEntity.setName(rs.getString("NAME"));
                userEntity.setLocation(rs.getString("LOCATION"));

                return userEntity;
            }
        });

        objList.forEach(obj -> System.out.println(obj.getUserName()+" "+obj.getName()+" "+obj.getLocation()));
    }
}
