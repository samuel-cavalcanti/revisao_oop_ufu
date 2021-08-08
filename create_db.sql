DROP TABLE IF EXISTS Books;
DROP TABLE IF EXISTS Reviews;

CREATE TABLE Books
(
    -- public final String title;
    -- public final String author;
    -- public final String year;
    book_id INTEGER PRIMARY KEY,
    title   TEXT NOT NULL,
    author  TEXT NOT NULL,
    year    TEXT NOT NULL
);

INSERT INTO Books (book_id, title, author, year)
VALUES (0, 'Clean Code', 'Robert C. Martin (Uncle Bob)', '2008');

INSERT INTO Books (book_id, title, author, year)
VALUES (1, 'clean architecture', 'Robert C. Martin (Uncle Bob)', '2017');

INSERT INTO Books (book_id, title, author, year)
VALUES (2, 'Test Driven Development: By Example', 'Kent Beck', '2010');


CREATE TABLE Reviews
(
    -- public final String title;
    -- public final String organization;
    -- public final String volume;
    -- public final String number;
    -- public final String year;
    Review_id    INTEGER PRIMARY KEY,
    title        TEXT NOT NULL,
    organization TEXT NOT NULL,
    volume       TEXT NOT NULL,
    number       TEXT NOT NULL,
    year         TEXT NOT NULL
);

INSERT INTO Reviews (Review_id, title, organization, volume, number, year)
VALUES (0, 'fofoca 1', 'preguiçoso', 'Vol 1', '1532-96', '1995');

INSERT INTO Reviews (Review_id, title, organization, volume, number, year)
VALUES (1, 'fofoca 2', 'preguiçoso', 'Vol 2', '1532-98', '2001');

INSERT INTO Reviews (Review_id, title, organization, volume, number, year)
VALUES (2, 'fofoca 3 não para', 'preguiçoso', 'Vol 3', '1535-85', '2020');