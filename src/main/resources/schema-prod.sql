create sequence hibernate_sequence start 1 increment 1;
create table authority (name varchar(50) not null, primary key (name));
create table batman_user (id int8 not null, club varchar(254), email varchar(50), first_name varchar(50), gender int4 not null, last_name varchar(50), password_hash varchar(60) not null, primary key (id));
create table user_authority (user_id int8 not null, authority_name varchar(50) not null, primary key (user_id, authority_name));
alter table batman_user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table if exists user_authority add constraint FK6ktglpl5mjosa283rvken2py5 foreign key (authority_name) references authority;
alter table if exists user_authority add constraint FKpqlsjpkybgos9w2svcri7j8xy foreign key (user_id) references batman_user;
