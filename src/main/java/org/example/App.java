package org.example;

import org.example.Entities.BookEntity;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Hello world!
 *
 */

@ComponentScan(basePackages = {"org.example.Configuration"})
public class App 
{
//    @Autowired
//    private BasicBean basicBean;
//
//    @Autowired
//    private UserDao user;
//
//    @Autowired
//    private UserDao2 user2;
//
//    @Autowired
//    private BookDao book;

    @Autowired
    BookRepository bookRepository;

    public static void main( String[] args )
    {
//        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigClass.class);
//        BasicBean obj = context.getBean(BasicBean.class);
//        obj.printHello();
//        System.out.println( "Hello World!" );

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        App app = context.getBean(App.class);
        app.start();
//        List<BookEntity> bookList = app.findByName("A TIME TO KILL",1);
//        bookList.forEach(obj -> System.out.println(obj));
    }

    public void start() {
//        basicBean.setName("daksh");
//        basicBean.printHello();
//        user.printUsers();
//        user2.printUsers();
//        book.insertEntry();
//        book.printBooks();
//        System.out.println(book.getCountOfRecords());


//        System.out.println("Count "+ bookRepository.getCountDistinctBooks());
//        BookEntity testBook = new BookEntity(5,"A TIME TO KILL", new Date(2021-1900,9,27));
//        updateBook(testBook);
        List<BookEntity> bookList = findByName("A TIME TO KILL",1);
        bookList.forEach(obj -> System.out.println(obj));
    }

    @Transactional
    public List<BookEntity> getAllBooks() {
        return (List<BookEntity>) bookRepository.findAll();
    }

    @Transactional
    public List<BookEntity> findByName(String name, Integer id){
//        return bookRepository.findByBookName(name);
//        return bookRepository.findByBookNameLike(name);
//        return bookRepository.findByBookNameIsContaining(name);
//        return bookRepository.getBookByBookName(name);
//        return bookRepository.getBookByBookID(Arrays.asList(1,2,3));
        return bookRepository.getBookByBookNameNative(Arrays.asList("dummy_book_name", "As I Lay Dying "));
    }

    @Transactional
    public BookEntity getById(Integer id){
        Optional<BookEntity> optional = bookRepository.findById(id);
        if(optional.isPresent())
            return optional.get();
        else
            return new BookEntity();
    }

    @Transactional
    public void deleteBook(BookEntity book){
        bookRepository.delete(book);
    }

    @Transactional
    public boolean addBook(BookEntity book){
        return bookRepository.save(book) != null;
    }

    @Transactional
    public boolean updateBook(BookEntity book){
        return bookRepository.save(book) != null;
    }



}
