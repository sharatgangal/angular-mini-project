package com.dxc.book.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dxc.book.model.Book;

/**
 * @author sgangal2
 *
 */
@Repository
//BookRepo extending mongorepository 
//Spring @Repository annotation is used to indicate that the class provides the mechanism for storage
//retrieval, search, update and delete operation on objects.

public interface BookRepository extends MongoRepository<Book, Object> {

}
