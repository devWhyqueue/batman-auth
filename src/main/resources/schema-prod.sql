create sequence hibernate_sequence start 1 increment 1;
create table authority
(
    name varchar(50) not null,
    primary key (name)
);
create table batman_user
(
    id             int8        not null,
    activated      boolean     not null,
    activation_key varchar(20),
    club           varchar(50) not null,
    email          varchar(50) not null,
    first_name     varchar(50) not null,
    gender         int4        not null,
    last_name      varchar(50) not null,
    password_hash  varchar(60) not null,
    primary key (id)
);
create table user_authority
(
    user_id        int8        not null,
    authority_name varchar(50) not null,
    primary key (user_id, authority_name)
);
alter table if exists batman_user
    add constraint UK_wvlmcee3y5aluek7jlmsxcej unique (email);
alter table if exists user_authority
    add constraint FK6ktglpl5mjosa283rvken2py5 foreign key (authority_name) references authority;
alter table if exists user_authority
    add constraint FKtg73yvvl84pwvhplu0m2pl8hk foreign key (user_id) references batman_user;
