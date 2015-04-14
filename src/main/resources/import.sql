insert into Book (id, title) values (1, 'Mistrz i Małgorzata');
insert into Book (id, title) values (2, 'Ania z Zielonego Wzgórza');
insert into Book (id, title) values (3, 'Lalka');
insert into Book (id, title) values (4, 'Praca Zbiorowa');

insert into Author (id, first_name, last_name, age) values (1, 'Michaił', 'Bułhakow', 44);
insert into Author (id, first_name, last_name, age) values (2, 'Lucy', 'Montgomery', 27);
insert into Author (id, first_name, last_name, age) values (3, 'Bolesław', 'Prus', 66);
insert into Author (id, first_name, last_name, age) values (4, 'Jan', 'Kowalski', 50);
insert into Author (id, first_name, last_name, age) values (5, 'Janusz', 'Nowak', 50);
insert into Author (id, first_name, last_name, age) values (6, 'Marek', 'Nowicki', 50);

insert into book_author (BOOK_ID, AUTHOR_ID) values (1, 1);
insert into book_author (BOOK_ID, AUTHOR_ID) values (2, 2);
insert into book_author (BOOK_ID, AUTHOR_ID) values (3, 3);
insert into book_author (BOOK_ID, AUTHOR_ID) values (4, 4);
insert into book_author (BOOK_ID, AUTHOR_ID) values (4, 5);
insert into book_author (BOOK_ID, AUTHOR_ID) values (4, 6);