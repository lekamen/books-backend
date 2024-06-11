USE Books;

SET @english_language_id = (SELECT id FROM languages WHERE code = 'en');

SET @query := CONCAT('ALTER TABLE books ADD COLUMN original_language_id BIGINT UNSIGNED NOT NULL DEFAULT ', @english_language_id);
PREPARE dynamic_statement FROM @query;
EXECUTE dynamic_statement;

ALTER TABLE books ADD CONSTRAINT fk_original_language FOREIGN KEY (original_language_id) REFERENCES languages(id);

DELETE FROM book_supported_language WHERE language_id = @english_language_id;