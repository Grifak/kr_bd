create table carstation
(
    id bigserial primary key,
    name varchar(100) not null
);

create table employee
(
    id bigserial primary key,
    first_name varchar(55) not null,
    last_name varchar(55) not null,
    patronymic varchar(55),
    email varchar(55) not null unique ,
    post varchar(55) not null,
    carstation_id bigint references carstation(id) not null,

    password varchar(55) not null
);

create table document
(
    id bigserial primary key,
    name varchar(55) not null ,
    user_sign varchar(55),
    carstation_sign varchar(55)
);

create table users
(
    id bigserial primary key ,
    first_name varchar(55) not null,
    last_name varchar(55) not null,
    patronymic varchar(55),
    card_number varchar(55) not null ,
    phone_number varchar(55) not null ,
    email varchar(55) not null unique,

    password varchar(55) not null
);

create table car
(
    id bigserial primary key,
    vin varchar(45) not null,
    registration_number varchar(45) not null unique,
    model varchar(45) not null ,
    brand varchar(45) not null,
    user_id bigint references users(id)
);

create table bid
(
    id bigserial primary key,
    name varchar(100),
    status varchar(45) not null,
    bid_amount bigint not null,
    author_id bigint references users(id) not null ,
    employee_id bigint references employee(id)
);

create table bid_car_doc
(
    id bigserial primary key ,
    bid_id bigint references bid(id) not null ,
    car_id bigint references car(id) not null ,
    doc_id bigint references document(id)
);