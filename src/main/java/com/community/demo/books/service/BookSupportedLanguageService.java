package com.community.demo.books.service;

import com.community.demo.books.model.entity.BookSupportedLanguage;
import com.community.demo.books.repository.BookSupportedLanguageRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class BookSupportedLanguageService {

  private final BookSupportedLanguageRepository bookSupportedLanguageRepository;

  public BookSupportedLanguageService(BookSupportedLanguageRepository bookSupportedLanguageRepository) {
    this.bookSupportedLanguageRepository = bookSupportedLanguageRepository;
  }

  public Mono<List<BookSupportedLanguage>> findByBookId(Long bookId) {
    return Mono.fromCallable(() -> bookSupportedLanguageRepository.findByBook_Id(bookId))
        .subscribeOn(Schedulers.boundedElastic());
  }
}
