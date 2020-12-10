-- 1
CREATE DATABASE converter
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;



-- 2
create table exchange_rates
(
    rates_id   serial  not null
        constraint exchange_rates_pk
            primary key,
    rates_date integer not null
);

alter table exchange_rates
    owner to postgres;



-- 3
create table foreign_currency_market
(
    id serial not null
        constraint foreign_currency_market_pk
            primary key,
    valute_id varchar(140) not null,
    num_code smallint not null,
    char_code varchar(4) not null,
    nominal int not null,
    name varchar(10) not null,
    value real not null,
    ex_rates_id int not null
        constraint foreign_currency_market_ex_rates_id_fk
            references exchange_rates
            on update cascade on delete cascade
);



-- 4
create table conversion_history
(
    id serial not null
        constraint conversion_history_pk
            primary key,
    history_date int not null,
    original_value real not null,
    result_value real not null,
    original_currency varchar(255) not null,
    result_currency varchar(255) not null,
    original_char_code varchar(4) not null,
    result_char_code varchar(4) not null
);