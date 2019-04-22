insert into AUTHOR (id, fname, lname) values (1, 'Bayan', 'Shiryanov');
insert into AUTHOR (id, fname, lname) values (2, 'Alex', 'Pushkin');
insert into AUTHOR (id, fname, lname) values (3, 'Mike', 'Lermontov');
insert into GENRE (id, name) values (1, 'Poem');
insert into GENRE (id, name) values (2, 'Novel');
insert into GENRE (id, name) values (3, 'Labuda')
insert into USER (id) values ('SuperAdmin');
insert into BOOK (id, name, author_id, genre_id) values (1, 'Mziry', 3, 1);
insert into BOOK (id, name, author_id, genre_id) values (2, 'Ruslan i Lyudmila', 2, 1);
insert into BOOK (id, name, author_id, genre_id) values (3, 'Kapitanskaya doch', 2, 2);
insert into BOOK (id, name, author_id, genre_id) values (4, 'Probel', 1, 3);
insert into COMMENT (id, book_id, user_id, comment) values (1, 4, 'SuperAdmin', 'This book is bullshit')