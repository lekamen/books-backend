package com.community.demo.books.service;

import com.community.demo.books.mapper.BookMapper;
import com.community.demo.books.model.dto.BookDto;
import com.community.demo.books.model.dto.CreateBookDto;
import com.community.demo.books.model.dto.UpdateBookDto;
import com.community.demo.books.model.entity.Book;
import com.community.demo.books.model.entity.Language;
import com.community.demo.books.repository.BookRepository;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class BookService {

  private final BookRepository bookRepository;
  private final Language defaultLanguage;
  private final LanguageService languageService;

  public BookService(BookRepository bookRepository, LanguageService languageService) {
    this.bookRepository = bookRepository;
    this.defaultLanguage = languageService.getDefaultLanguage().block();
    this.languageService = languageService;
  }

  public Mono<BookDto> create(CreateBookDto dto) {
    return Mono.fromCallable(() -> bookRepository.save(BookMapper.create(dto, defaultLanguage)))
        .subscribeOn(Schedulers.boundedElastic())
        .map(BookMapper::toBookDto);
  }

  public Mono<BookDto> get(Long id) {
    return Mono.fromCallable(() -> bookRepository.findById(id))
        .subscribeOn(Schedulers.boundedElastic())
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(BookMapper::toBookDto)
        .switchIfEmpty(Mono.error(new IllegalArgumentException()));
  }

  public Flux<BookDto> getAll() {
    return Flux.fromIterable(bookRepository.findAll())
        .map(BookMapper::toBookDto)
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<BookDto> update(Long id, UpdateBookDto book) {
    return Mono.fromCallable(() -> bookRepository.findById(id))
        .subscribeOn(Schedulers.boundedElastic())
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(existingBook -> bookRepository.save(BookMapper.update(existingBook, book)))
        .map(BookMapper::toBookDto)
        .switchIfEmpty(Mono.error(new IllegalArgumentException()));
  }

  public Mono<Void> delete(Long id) {
    return Mono.fromRunnable(() -> bookRepository.deleteById(id))
        .then()
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<Book> updateLanguages(Long id, Set<Long> languageIds) {
    return Mono.fromCallable(() -> bookRepository.findById(id))
        .subscribeOn(Schedulers.boundedElastic())
        .filter(Optional::isPresent)
        .switchIfEmpty(Mono.error(new IllegalArgumentException())) //TODO: custom exception?
        .map(Optional::get)
        .zipWith(languageService.findByIds(languageIds))
        .map(pair -> bookRepository.save(BookMapper.updateLanguages(pair.getT1(), pair.getT2())));
  }
}