package com.community.demo.books.controller;


import com.community.demo.books.model.dto.BookDto;
import com.community.demo.books.model.dto.CreateBookDto;
import com.community.demo.books.model.dto.SupportedLanguagesDto;
import com.community.demo.books.model.dto.UpdateBookDto;
import com.community.demo.books.model.dto.UpdateLanguagesDto;
import com.community.demo.books.service.BookService;
import java.util.HashSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping
  public Mono<BookDto> create(@RequestBody CreateBookDto book) {
    return bookService.create(book);
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<BookDto>> get(@PathVariable Long id) {
    return bookService.get(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}/languages")
  public Mono<ResponseEntity<SupportedLanguagesDto>> getSupportedLanguages(@PathVariable Long id) {
    return bookService.getSupportedLanguages(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping
  public Flux<BookDto> getAll() {
    return bookService.getAll();
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<BookDto>> update(@PathVariable Long id, @RequestBody UpdateBookDto book) {
    return bookService.update(id, book)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Object>> delete(@PathVariable Long id) {
    return bookService.delete(id)
        .then(Mono.just(ResponseEntity.noContent().build()))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}/languages")
  public Mono<ResponseEntity<BookDto>> updateLanguages(@PathVariable Long id, @RequestBody UpdateLanguagesDto dto) {
    return bookService.updateLanguages(id, new HashSet<>(dto.languageIds()))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}