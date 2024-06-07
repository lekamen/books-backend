package com.community.demo.books.mapper;

import com.community.demo.books.model.dto.CreateLanguageDto;
import com.community.demo.books.model.dto.LanguageDto;
import com.community.demo.books.model.dto.UpdateLanguageDto;
import com.community.demo.books.model.entity.Language;

public class LanguageMapper {

  private LanguageMapper() {}

  public static LanguageDto toLanguageDto(Language language) {
    return LanguageDto.builder()
        .id(language.getId())
        .name(language.getName())
        .code(language.getCode())
        .build();
  }

  public static Language toLanguage(LanguageDto languageDto) {
    Language language = new Language();
    language.setId(languageDto.id());
    language.setName(languageDto.name());
    language.setCode(languageDto.code());

    return language;
  }

  public static Language update(Language existingLanguage, UpdateLanguageDto dto) {
    existingLanguage.setName(dto.name());

    return existingLanguage;
  }

  public static Language create(CreateLanguageDto dto) {
    Language language = new Language();
    language.setName(dto.name());
    language.setCode(dto.code());

    return language;
  }
}
