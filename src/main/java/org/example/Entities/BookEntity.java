package org.example.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Access(AccessType.FIELD)
@Table(name = "books")
public class BookEntity {

    public BookEntity() {
    }
    public BookEntity(String bookName, Date registeredDate) {
        this.bookName = bookName;
        this.registeredDate = registeredDate;
    }

    public BookEntity(Integer bookID, String bookName, Date registeredDate) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.registeredDate = registeredDate;
    }


    public Integer bookID;
    public String bookName;
    public Date registeredDate;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    @Column(name = "name")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Column(name = "registered_date")
    @Temporal(TemporalType.DATE)
    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    @Override
    public String toString(){
        return getBookID()+" "+getBookName()+" "+getRegisteredDate();
    }
}
