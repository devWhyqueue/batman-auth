DELETE
FROM User_Authority
WHERE true;
DELETE
FROM Batman_User
WHERE true;
DELETE
FROM Authority
WHERE true;

INSERT INTO Authority
VALUES ('ROLE_USER');
INSERT INTO Authority
VALUES ('ROLE_ANONYMOUS');
INSERT INTO Authority
VALUES ('ROLE_ADMIN');
INSERT INTO Authority
VALUES ('ROLE_SYSTEM');

INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club, activated)
VALUES (1, 'registration-service@batman.de',
        '$2y$10$.6YcluwbfqoBLtzMwGK7X.PF7lP/ZvhBQRvDaMr741Z50UIIo9QLC', 'Registration', 'Service',
        0, 'Batman', true);
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club, activated)
VALUES (2, 'auth-service@batman.de',
        '$2y$10$.6YcluwbfqoBLtzMwGK7X.PF7lP/ZvhBQRvDaMr741Z50UIIo9QLC', 'Auth', 'Service',
        0, 'Batman', true);
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club, activated)
VALUES (3, 'max@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Max',
        'Mustermann', 0, 'SV Musterstadt', true);
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club, activated)
VALUES (4, 'marius@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Marius',
        'Mustermann', 0, 'SV Musterstadt', true);
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club, activated)
VALUES (5, 'maja@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Maja',
        'Mustermann', 1, 'SV Musterstadt', true);
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club, activated)
VALUES (6, 'maren@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Maren',
        'Mustermann', 1, 'SV Musterstadt', true);
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club, activated)
VALUES (7, 'mark@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Mark',
        'Müller', 0, 'SV Müllersbach', true);
INSERT INTO Batman_User (id, email, password_hash, first_name, last_name, gender, club, activated)
VALUES (8, 'maria@a.de', '$2a$10$5jTV8V7ovQe2KeVZOTam..5.dxaoJgDMHLWpqJGh/HKqwcl3xpuue', 'Maria',
        'Müller', 1, 'SV Müllersbach', true);

INSERT INTO User_Authority (user_id, authority_name)
VALUES (1, 'ROLE_SYSTEM');
INSERT INTO User_Authority (user_id, authority_name)
VALUES (2, 'ROLE_SYSTEM');
INSERT INTO User_Authority (user_id, authority_name)
VALUES (3, 'ROLE_USER');
INSERT INTO User_Authority (user_id, authority_name)
VALUES (4, 'ROLE_USER');
INSERT INTO User_Authority (user_id, authority_name)
VALUES (5, 'ROLE_USER');
INSERT INTO User_Authority (user_id, authority_name)
VALUES (6, 'ROLE_USER');
INSERT INTO User_Authority (user_id, authority_name)
VALUES (7, 'ROLE_USER');
INSERT INTO User_Authority (user_id, authority_name)
VALUES (8, 'ROLE_USER');

ALTER SEQUENCE Hibernate_Sequence RESTART WITH 20;
