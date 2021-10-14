package org.example.repository;

import org.example.Entities.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    List<BookEntity> findByBookName(String bookName);
//    List<BookEntity> findByBookIDandBookName(Integer bookID, String bookName);

    List<BookEntity> findByBookNameLike(String bookName);
    List<BookEntity> findByBookNameIsContaining(String bookName);

    @Query("SELECT b FROM BookEntity b WHERE b.bookName = ?1")
    List<BookEntity> getBookByBookName(@Param("bookName") String bookName);

    @Query("SELECT b FROM BookEntity b WHERE b.bookID IN :bookIDs")
    List<BookEntity> getBookByBookID(@Param("bookIDs") List<Integer> bookID);

    @Query(value = "SELECT * FROM BOOKS WHERE name IN :bookName", nativeQuery = true)
    List<BookEntity> getBookByBookNameNative(@Param("bookName") List<String> bookNames);

    @Query(value = "SELECT count(DISTINCT(NAME)) FROM BOOKS", nativeQuery = true)
    Integer getCountDistinctBooks();

}
