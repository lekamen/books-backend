package com.community.demo.books.mapper;

import com.community.demo.books.model.dto.CreateLanguageDto;
import com.community.demo.books.model.dto.LanguageDto;
import com.community.demo.books.model.dto.SupportedLanguagesDto;
import com.community.demo.books.model.dto.UpdateLanguageDto;
import com.community.demo.books.model.entity.BookSupportedLanguage;
import com.community.demo.books.model.entity.Language;
import java.util.List;

public class LanguageMapper {

  private LanguageMapper() {}

  public static LanguageDto toLanguageDto(Language language) {
    return LanguageDto.builder()
        .id(language.getId())
        .name(language.getName())
        .code(language.getCode())
        .build();
  }

  public static LanguageDto toLanguageDto(BookSupportedLanguage language) {
    return LanguageDto.builder()
        .id(language.getLanguage().getId())
        .name(language.getLanguage().getName())
        .code(language.getLanguage().getCode())
        .build();
  }

  public static Language toLanguage(LanguageDto languageDto) {
    Language language = new Language();
    language.setId(languageDto.id());
    language.setName(languageDto.name());
    language.setCode(languageDto.code());

    return language;
  }

  public static SupportedLanguagesDto toSupportedLanguagesDto(List<BookSupportedLanguage> languages) {
    return SupportedLanguagesDto.builder()
        .supportedLanguages(languages.stream()
            .map(LanguageMapper::toLanguageDto)
            .toList())
        .build();
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
