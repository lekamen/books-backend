package com.community.demo.books.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class BookSupportedLanguagePK implements Serializable {

  @Column(name = "book_id")
  private Long bookId;

  @Column(name = "language_id")
  private Long languageId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookSupportedLanguagePK that = (BookSupportedLanguagePK) o;
    return Objects.equals(getBookId(), that.getBookId())
        && Objects.equals(getLanguageId(), that.getLanguageId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBookId(), getLanguageId());
  }
}
