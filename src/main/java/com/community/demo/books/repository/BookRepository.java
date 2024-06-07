package com.community.demo.books.repository;

import com.community.demo.books.model.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllByIsPublished(boolean isPublished);
}