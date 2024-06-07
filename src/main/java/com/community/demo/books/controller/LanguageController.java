package com.community.demo.books.controller;

import com.community.demo.books.model.dto.CreateLanguageDto;
import com.community.demo.books.model.dto.LanguageDto;
import com.community.demo.books.model.dto.UpdateLanguageDto;
import com.community.demo.books.service.LanguageService;
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
@RequestMapping("/languages")
@Slf4j
public class LanguageController {

  private final LanguageService languageService;

  public LanguageController(LanguageService languageService) {
    this.languageService = languageService;
  }

  @PostMapping
  public Mono<LanguageDto> create(@RequestBody CreateLanguageDto dto) {
    return languageService.create(dto);
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<LanguageDto>> get(@PathVariable Long id) {
    return languageService.get(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping
  public Flux<LanguageDto> getAll() {
    return languageService.getAll();
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<LanguageDto>> update(@PathVariable Long id, @RequestBody UpdateLanguageDto dto) {
    return languageService.update(id, dto)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Object>> delete(@PathVariable Long id) {
    return languageService.delete(id)
        .then(Mono.just(ResponseEntity.noContent().build()))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}