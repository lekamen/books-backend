USE Books;

INSERT INTO languages (name, code) VALUES
           ('English', 'en'),
           ('Mandarin', 'zh'),
           ('Spanish', 'es'),
           ('Hindi', 'hi'),
           ('French', 'fr'),
           ('Arabic', 'ar'),
           ('Bengali', 'bn'),
           ('Russian', 'ru'),
           ('Portuguese', 'pt'),
           ('Indonesian', 'id'),
           ('Urdu', 'ur'),
           ('German', 'de'),
           ('Japanese', 'ja'),
           ('Swahili', 'sw'),
           ('Marathi', 'mr');

INSERT INTO books (name, author, publisher, is_published, is_international) VALUES
            ('To Kill a Mockingbird', 'Harper Lee', 'HarperCollins Publishers', true, true),
            ('The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', true, true),
            ('Harry Potter and the Philosopher''s Stone', 'J.K. Rowling', 'Bloomsbury Publishing', true, true),
            ('The Lord of the Rings', 'J.R.R. Tolkien', 'Allen & Unwin', true, true),
            ('The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', true, true),
            ('Pride and Prejudice', 'Jane Austen', 'T. Egerton, Whitehall', true, true),
            ('1984', 'George Orwell', 'Secker and Warburg', true, true),
            ('The Diary of a Young Girl', 'Anne Frank', 'Contact Publishing', true, true),
            ('The Alchemist', 'Paulo Coelho', 'HarperCollins', true, true),
            ('The Hobbit', 'J.R.R. Tolkien', 'Allen & Unwin', true, true),
            ('The Little Prince', 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', true, true),
            ('The Da Vinci Code', 'Dan Brown', 'Doubleday', true, true),
            ('The Adventures of Huckleberry Finn', 'Mark Twain', 'Chatto & Windus', true, true),
            ('The Hunger Games', 'Suzanne Collins', 'Scholastic Corporation', true, true),
            ('The Chronicles of Narnia', 'C.S. Lewis', 'Geoffrey Bles', true, true),
            ('The Kite Runner', 'Khaled Hosseini', 'Riverhead Books', true, true),
            ('A Tale of Two Cities', 'Charles Dickens', 'Chapman & Hall', true, true),
            ('Animal Farm', 'George Orwell', 'Secker and Warburg', true, true),
            ('The Fault in Our Stars', 'John Green', 'Dutton Books', true, true),
            ('The Girl with the Dragon Tattoo', 'Stieg Larsson', 'Norstedts förlag', true, true);

-- English translations
INSERT INTO book_supported_language (book_id, language_id) VALUES
       (1, 1), -- To Kill a Mockingbird
       (2, 1), -- The Catcher in the Rye
       (3, 1), -- Harry Potter and the Philosopher's Stone
       (4, 1), -- The Lord of the Rings
       (5, 1), -- The Great Gatsby
       (6, 1), -- Pride and Prejudice
       (7, 1), -- 1984
       (8, 1); -- The Diary of a Young Girl

-- Spanish translations
INSERT INTO book_supported_language (book_id, language_id) VALUES
       (1, 4), -- To Kill a Mockingbird
       (2, 4), -- The Catcher in the Rye
       (3, 4), -- Harry Potter and the Philosopher's Stone
       (4, 4), -- The Lord of the Rings
       (5, 4), -- The Great Gatsby
       (6, 4), -- Pride and Prejudice
       (7, 4), -- 1984
       (8, 4); -- The Diary of a Young Girl

-- French translations
INSERT INTO book_supported_language (book_id, language_id) VALUES
       (1, 5), -- To Kill a Mockingbird
       (2, 5), -- The Catcher in the Rye
       (3, 5), -- Harry Potter and the Philosopher's Stone
       (4, 5), -- The Lord of the Rings
       (5, 5), -- The Great Gatsby
       (6, 5), -- Pride and Prejudice
       (7, 5), -- 1984
       (8, 5); -- The Diary of a Young Girl