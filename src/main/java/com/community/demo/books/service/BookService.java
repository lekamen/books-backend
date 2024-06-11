package com.community.demo.books.service;

import com.community.demo.books.mapper.BookMapper;
import com.community.demo.books.mapper.LanguageMapper;
import com.community.demo.books.model.dto.BookDto;
import com.community.demo.books.model.dto.CreateBookDto;
import com.community.demo.books.model.dto.SupportedLanguagesDto;
import com.community.demo.books.model.dto.UpdateBookDto;
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
  private final BookSupportedLanguageService bookSupportedLanguageService;

  public BookService(BookRepository bookRepository, LanguageService languageService, BookSupportedLanguageService bookSupportedLanguageService) {
    this.bookRepository = bookRepository;
    this.defaultLanguage = languageService.getDefaultLanguage().block();
    this.languageService = languageService;
    this.bookSupportedLanguageService = bookSupportedLanguageService;
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

  public Mono<SupportedLanguagesDto> getSupportedLanguages(Long id) {
    return bookSupportedLanguageService.findByBookId(id)
        .map(LanguageMapper::toSupportedLanguagesDto);
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
        .zipWith(languageService.findById(book.originalLanguageId()))
        .map(pair -> bookRepository.save(BookMapper.update(pair.getT1(), book, pair.getT2())))
        .map(BookMapper::toBookDto)
        .switchIfEmpty(Mono.error(new IllegalArgumentException()));
  }

  public Mono<Void> delete(Long id) {
    return Mono.fromRunnable(() -> bookRepository.deleteById(id))
        .then()
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<BookDto> updateLanguages(Long id, Set<Long> languageIds) {
    return Mono.fromCallable(() -> bookRepository.findById(id))
        .subscribeOn(Schedulers.boundedElastic())
        .filter(Optional::isPresent)
        .switchIfEmpty(Mono.error(new IllegalArgumentException())) //TODO: custom exception?
        .map(Optional::get)
        .zipWith(languageService.findByIds(languageIds))
        .map(pair -> bookRepository.save(BookMapper.updateLanguages(pair.getT1(), pair.getT2())))
        .map(BookMapper::toBookDto);
  }
}