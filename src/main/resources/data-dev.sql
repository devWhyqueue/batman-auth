DELETE FROM User_Authority WHERE true;
DELETE FROM Batman_User WHERE true;
DELETE FROM Authority WHERE true;

INSERT INTO Authority VALUES ('ROLE_USER');
INSERT INTO Authority VALUES ('ROLE_ANONYMOUS');
INSERT INTO Authority VALUES ('ROLE_ADMIN');

INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club) VALUES (1, 'max@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Max', 'Mustermann', 0, 'SV Musterstadt');
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club) VALUES (2, 'marius@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Marius', 'Mustermann', 0, 'SV Musterstadt');
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club) VALUES (3, 'maja@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Maja', 'Mustermann', 1, 'SV Musterstadt');
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club) VALUES (4, 'maren@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Maren', 'Mustermann', 1, 'SV Musterstadt');
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club) VALUES (9, 'mark@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Mark', 'Müller', 0, 'SV Müllersbach');
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club) VALUES (10, 'maria@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Maria', 'Müller', 1, 'SV Müllersbach');

INSERT INTO User_Authority (user_id, authority_name) VALUES (1, 'ROLE_USER');
INSERT INTO User_Authority (user_id, authority_name) VALUES (2, 'ROLE_USER');
INSERT INTO User_Authority (user_id, authority_name) VALUES (3, 'ROLE_USER');
INSERT INTO User_Authority (user_id, authority_name) VALUES (4, 'ROLE_USER');
INSERT INTO User_Authority (user_id, authority_name) VALUES (9, 'ROLE_USER');
INSERT INTO User_Authority (user_id, authority_name) VALUES (10, 'ROLE_USER');

ALTER SEQUENCE Hibernate_Sequence RESTART WITH 20;
