package com.community.demo.books.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@AllArgsConstructor
@Accessors(fluent = true, chain = false)
@Builder(builderClassName = "Builder", toBuilder = true)
@EqualsAndHashCode
public class CreateBookDto {

  String name;
  String author;
  String publisher;
  String originalLanguage;
  boolean isPublished;
}
