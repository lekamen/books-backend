package com.community.demo.books.mapper;

import com.community.demo.books.model.dto.BookDto;
import com.community.demo.books.model.dto.CreateBookDto;
import com.community.demo.books.model.dto.UpdateBookDto;
import com.community.demo.books.model.entity.Book;
import com.community.demo.books.model.entity.Language;
import java.util.Set;

public class BookMapper {

  private BookMapper() {}

  public static BookDto toBookDto(Book book) {
    return BookDto.builder()
        .id(book.getId())
        .name(book.getName())
        .author(book.getAuthor())
        .publisher(book.getPublisher())
        .isPublished(book.isPublished())
        .isInternational(book.isInternational())
        .build();
  }

  public static Book update(Book existingBook, UpdateBookDto book) {
    existingBook.setName(book.name());
    existingBook.setAuthor(book.author());
    existingBook.setPublisher(book.publisher());
    existingBook.setPublished(book.isPublished());

    return existingBook;
  }

  public static Book create(CreateBookDto dto, Language language) {
    Book book = new Book();
    book.setName(dto.name());
    book.setAuthor(dto.author());
    book.setPublisher(dto.publisher());
    book.setPublished(dto.isPublished());
    book.setInternational(false);

    book.addLanguage(language);
    return book;
  }

  public static Book updateLanguages(Book book, Set<Language> languages) {
    book.setLanguages(languages);
    return book;
  }
}
