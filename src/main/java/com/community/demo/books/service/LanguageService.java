package com.community.demo.books.service;

import com.community.demo.books.mapper.LanguageMapper;
import com.community.demo.books.model.dto.CreateLanguageDto;
import com.community.demo.books.model.dto.LanguageDto;
import com.community.demo.books.model.dto.UpdateLanguageDto;
import com.community.demo.books.model.entity.Language;
import com.community.demo.books.repository.LanguageRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class LanguageService {

  private final LanguageRepository languageRepository;

  public LanguageService(LanguageRepository languageRepository) {
    this.languageRepository = languageRepository;
  }

  public Mono<LanguageDto> create(CreateLanguageDto dto) {
    return Mono.fromCallable(() -> languageRepository.save(LanguageMapper.create(dto)))
        .subscribeOn(Schedulers.boundedElastic())
        .map(LanguageMapper::toLanguageDto);
  }

  public Mono<LanguageDto> get(Long id) {
    return Mono.fromCallable(() -> languageRepository.findById(id))
        .subscribeOn(Schedulers.boundedElastic())
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(LanguageMapper::toLanguageDto)
        .switchIfEmpty(Mono.error(new IllegalArgumentException()));
  }

  public Flux<LanguageDto> getAll() {
    return Flux.fromIterable(languageRepository.findAll())
        .subscribeOn(Schedulers.boundedElastic())
        .map(LanguageMapper::toLanguageDto);
  }

  public Mono<LanguageDto> update(Long id, UpdateLanguageDto dto) {
    return Mono.fromCallable(() -> languageRepository.findById(id))
        .subscribeOn(Schedulers.boundedElastic())
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(existingLanguage -> languageRepository.save(LanguageMapper.update(existingLanguage, dto)))
        .map(LanguageMapper::toLanguageDto)
        .switchIfEmpty(Mono.error(new IllegalArgumentException()));
  }

  public Mono<Void> delete(Long id) {
    return Mono.fromRunnable(() -> languageRepository.deleteById(id))
        .then()
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Mono<Language> getDefaultLanguage() {
    return Mono.fromCallable(() -> languageRepository.findByCode("en"))
        .subscribeOn(Schedulers.boundedElastic())
        .filter(Optional::isPresent)
        .map(Optional::get)
        .switchIfEmpty(Mono.error(new IllegalArgumentException()));
  }

  public Mono<Set<Language>> findByIds(Set<Long> ids) {
    return Mono.fromCallable(() -> languageRepository.findAllById(ids))
        .subscribeOn(Schedulers.boundedElastic())
        .map(HashSet::new);
  }
}