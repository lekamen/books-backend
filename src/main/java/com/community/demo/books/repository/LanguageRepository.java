package com.community.demo.books.repository;


import com.community.demo.books.model.entity.Language;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {

  Optional<Language> findByCode(String code);
}