USE Books;

CREATE TABLE IF NOT EXISTS languages (
       id SERIAL PRIMARY KEY,
       name VARCHAR(100) NOT NULL UNIQUE,
       code VARCHAR(2) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS books (
       id SERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       author VARCHAR(255) NOT NULL,
       publisher VARCHAR(255),
       is_published BOOLEAN NOT NULL,
       is_international BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS book_supported_language (
     book_id BIGINT UNSIGNED NOT NULL,
     language_id BIGINT UNSIGNED NOT NULL,
     PRIMARY KEY (book_id, language_id),
     FOREIGN KEY (book_id) REFERENCES books(id),
     FOREIGN KEY (language_id) REFERENCES languages(id)
);