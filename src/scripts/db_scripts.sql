CREATE database niq_personal_info_db;
CREATE USER evgenyu WITH encrypted password '1234';
GRANT ALL PRIVILEGES ON database niq_personal_info_db TO evgenyu;


create table product (
    product_id varchar(255) not null,
    brand varchar(255),
    category varchar(255),
    primary key (product_id)
);

create table shopper (
    shopper_id varchar(255) not null,
    primary key (shopper_id)
);

create table shopper_product (
    product_id varchar(255) not null,
    shopper_id varchar(255) not null,
    relevant_score float(53) not null,
    primary key (product_id, shopper_id)
);

create or replace view shopper_product_view as
    select sp.shopper_id, sp.product_id, relevant_score, category, brand
    from shopper_product as sp
    inner join product p on sp.product_id = p.product_id;