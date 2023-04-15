create table users
(
    id bigserial primary key,
    login varchar(55) not null unique,
    password varchar(200) not null,
    email varchar(55) not null,
    created timestamp not null default CURRENT_TIMESTAMP
);

create table car
(
    id bigserial primary key,
    vin varchar(45) not null,
    registration_number varchar(45) not null unique,
    model varchar(45) not null,
    brand varchar(45) not null,
    horse_power int not null,
    user_id bigint references users(id)
);

create table modification
(
    id bigserial primary key,
    name varchar(55) not null,
    modif_power int
);

create table car_modification
(
    car_id bigint not null references car(id),
    modif_id bigint not null references modification(id)
);