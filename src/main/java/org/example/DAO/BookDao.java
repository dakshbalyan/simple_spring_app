package org.example.DAO;

import org.example.Entities.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Component
public class BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Properties properties;

    public void insertEntry(){

//        String query = "insert into books(name, registered_date) values('"+properties.getProperty("bookName")+"','"+ LocalDate.now()+"')";

//        jdbcTemplate.execute(query);
    }

    public void printBooks(){
        String query = "Select * from books where NAME = '"+properties.getProperty("bookName")+"'";
        List<BookEntity> objList = jdbcTemplate.query(query, new RowMapper<BookEntity>(){
            @Override
            public BookEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                BookEntity bookEntity = new BookEntity();
                bookEntity.setBookName(rs.getString("name"));
                bookEntity.setBookID(Integer.valueOf(rs.getString("id")));
                bookEntity.setRegisteredDate(rs.getDate("registered_date"));

                return bookEntity;
            }
        });

        objList.forEach(obj -> System.out.println(obj.getBookName()+" "+obj.getBookID()+" "+obj.getRegisteredDate()));
    }

    public int getCountOfRecords(){
        String query = "select count(*) from books";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);

        return count;
    }
}
