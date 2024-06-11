package com.community.demo.books.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_supported_language")
@Getter
@Setter
@NoArgsConstructor
public class BookSupportedLanguage {

  @EmbeddedId
  private BookSupportedLanguagePK id;

  @ManyToOne
  @MapsId("book_id")
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  private Book book;

  @ManyToOne
  @MapsId("language_id")
  @JoinColumn(name = "language_id", referencedColumnName = "id")
  private Language language;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookSupportedLanguage that = (BookSupportedLanguage) o;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
