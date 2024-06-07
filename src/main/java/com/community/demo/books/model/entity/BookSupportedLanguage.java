package com.community.demo.books.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  private Book book;

  @ManyToOne
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
    BookSupportedLanguage bsl = (BookSupportedLanguage) o;
    return Objects.equals(getId(), bsl.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
