package com.community.demo.books.repository;

import com.community.demo.books.model.entity.BookSupportedLanguage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookSupportedLanguageRepository extends JpaRepository<BookSupportedLanguage, Long> {

  List<BookSupportedLanguage> findByBook_Id(Long bookId);
}